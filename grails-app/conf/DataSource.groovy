import org.hibernate.dialect.H2Dialect
import org.hibernate.dialect.PostgreSQLDialect

dataSource {
    pooled = true
    driverClassName = "org.postgresql.Driver"
    dialect = PostgreSQLDialect
    //logSql=true
}

hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
//    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory' // Hibernate 3
    cache.region.factory_class = 'org.hibernate.cache.ehcache.EhCacheRegionFactory' // Hibernate 4
    singleSession = true // configure OSIV singleSession mode
    flush.mode = 'manual' // OSIV session flush mode outside of transactional context
}

// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "update"
            url = "jdbc:postgresql://localhost:5432/seminario"
            username = "seminario"
            password = "seminario"
        }
    }
    test {
        dataSource {
            dbCreate = "create"
            driverClassName = "org.h2.Driver"
            url = "jdbc:h2:mem:testDb"

            username = "sa"
            password = ""
            logSql=true
        }
    }

    production {
        dataSource {
            dbCreate = "update"

            uri = new URI(System.env.DATABASE_URL?:"postgres://vkcxjljvgnurou:u0wrvIcYnhz3YW8MC46gpq_p1_@ec2-54-204-40-96.compute-1.amazonaws.com:5432/d2vnk6ftmepod1")

            url = "jdbc:postgresql://"+uri.host+uri.path
            username = uri.userInfo.split(":")[0]
            password = uri.userInfo.split(":")[1]
        }
    }

}
