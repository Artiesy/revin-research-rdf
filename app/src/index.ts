import CacheManager from './cacheManager'

console.log("========== Blazegraph Improved Framework v0.1a =========")

let query1 = "SELECT * WHERE { ?s ?p ?o }";
let query2 = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
    "PREFIX xsd: <http://www.w3.org/2001/XMLSchema>\n" +
    "PREFIX my: <http://127.0.0.1/bg/ont/test1#>\n" +
    "SELECT ?Object\n" +
    "WHERE {\n" +
    "?Object my:has_id \"Object_10000\"\n" +
    "}";
let query3 = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
    "PREFIX xsd: <http://www.w3.org/2001/XMLSchema>\n" +
    "PREFIX my: <http://127.0.0.1/bg/ont/test1#>\n" +
    "SELECT ?Subject ?Object\n" +
    "WHERE\n" +
    "{\n" +
    "?Subject my:linked_to ?Object\n" +
    "}";
let query4 = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schemak>\n " +
    "PREFIX xsd: <http://www.w3.org/2001/XMLSchema>\n" +
    "PREFIX my: <http://127.0.0.1/bg/ont/test1#>\n" +
    "SELECT ?Object WHERE {\n" +
    "?Object my:has_parent_id/my:has_parent_id/my:linked_to ?Core_2_Level_5_18548 .\n" +
    "?Option my:has_parent_id/my:has_parent_id ?Core_2_Level_5_18548 .\n" +
    "?Option my:has_id ?Option_id .\n" +
    "FILTER (?Option_id = \"Option_10\")\n" +
    "}\n" + "LIMIT 100";


const executeCached = async (query: string) => {
    if (CacheManager.EXISTS(query)) return CacheManager.GET(query)

    const res = await fetch("http://127.0.0.1:8080/bigdata/sparql?query=" + encodeURIComponent(query))
    const result = await res.text()
    CacheManager.SETEX(query, 180, result)
    return result
}


(async () => {

    console.log("Executing queries...")
    let t1 = Date.now()
    await executeCached(query1)
        .then(res => {
            let dur = Date.now() - t1
            console.log("Query #1 time: " + dur + "ms")

            // console.log(res)
        })
        .then(() => {
            t1 = Date.now()
            executeCached(query2)
                .then(res => {
                    let dur = Date.now() - t1
                    console.log("Query #2 time: " + dur + "ms")

                    // console.log(res)
                })
                .then(() => {
                    t1 = Date.now()
                    executeCached(query3)
                        .then(res => {
                            let dur = Date.now() - t1
                            console.log("Query #3 time: " + dur + "ms")

                            // console.log(res)
                        })
                        .then(() => {
                            t1 = Date.now()
                            executeCached(query4)
                                .then(res => {
                                    let dur = Date.now() - t1
                                    console.log("Query #4 time: " + dur + "ms")

                                    // console.log(res)
                                })
                        })
                        .then(() => {
                            t1 = Date.now()
                            executeCached(query1)
                                .then(res => {
                                    let dur = Date.now() - t1
                                    console.log("Query #1 again time: " + dur + "ms")

                                    // console.log(res)
                                })
                        })
                })
        })




})()



