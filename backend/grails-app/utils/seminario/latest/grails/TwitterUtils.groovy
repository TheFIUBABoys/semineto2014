package seminario.latest.grails

class TwitterUtils {

    public static String getTweetUrl (String screenName, String tweetId) {
        return 'https://twitter.com/' + screenName + '/status/' + tweetId
    }
}
