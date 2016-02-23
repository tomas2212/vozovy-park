/**
 * Created by Tomas on 21/2/2016.
 */
var baseUrl="http://127.0.0.1:8090/pa165/resources/"
$(document).ready(function() {
    $.ajax({
        //url: "http://rest-service.guides.spring.io/greeting"
        url: baseUrl,
        settings: {method:"PUT"}
    }).then(function(data) {
            console.dir(data);
        $('.greeting-services').append(data.services);

        //$('.greeting-content').append(data.content);
    },
        function(err){
            console.error(err);
        }
    );
});