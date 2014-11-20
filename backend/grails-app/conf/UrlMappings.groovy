class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/service/$id/positive"(controller:"serviceStatus"){
            action = [GET:"positive"]
        }

        "/service/$id/negative"(controller:"serviceStatus"){
            action = [GET:"negative"]
        }

        "/service/"(controller:"serviceStatus"){
            action = [GET:"all"]
        }

//        "/"(view:"/index")
        "/"(view:"landingPage/index")
//        "grails-config"(view:"/index")
        "500"(view:'/error')
	}
}
