
var sensors = {
  "light": function (state) {
    console.log(state ? "light on" : "light off");
  },
  "humidity": function (value) {
    console.log("humidity = " + value);
  },
  "temperature": function (value) {
    console.log("temperature = " + value);
  }
};


////////// Connect and handle the Stream ///////

function connect () {
  var source = new EventSource("/stream");

  source.addEventListener('message', function(e) {
    var json = JSON.parse(e.data);
    for (var key in json)
      if (key in sensors)
        sensors[key](json[key]);
  }, false);

  source.addEventListener('open', function(e) {
    // Connection was opened.
  }, false);

  source.addEventListener('error', function(e) {
    if (e.readyState == EventSource.CLOSED) {
      // Connection was closed.
    }
  }, false);
}

connect();
