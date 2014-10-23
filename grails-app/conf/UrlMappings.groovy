class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

//        "/"(view:"/index")
        "/"(view:"landingPage/index")
//        "grails-config"(view:"/index")
        "500"(view:'/error')
	}
}
