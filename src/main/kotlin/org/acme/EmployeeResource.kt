package org.acme

import javax.ws.rs.GET
import javax.ws.rs.Path

@Path("/employee")
class EmployeeResource {

    @GET
    suspend fun employees(): List<Employee> {
        return emptyList()
//        return db.withTransaction {
//
//            // (3) get an entity metamodel
//            val e = Meta.employee
//
//            // (4) create schema
//            db.runQuery {
//                QueryDsl.create(e)
//            }
//
//            // (5) insert multiple employees at once
//            db.runQuery {
//                QueryDsl.insert(e).multiple(Employee(name = "AAA"), Employee(name = "BBB"))
//            }
//
//            // (6) select all
//            db.runQuery {
//                QueryDsl.from(e).orderBy(e.id)
//            }
//        }

    }
}