package seminario.latest.grails



class FetchTweetsJob {
    static triggers = {
      simple repeatInterval: 5000l // execute job once in 5 seconds
    }

    def execute() {
        System.out.println("I'm working")
    }
}
