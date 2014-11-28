var source = new EventSource("/stream");

source.addEventListener('message', function(e) {
  var json = JSON.parse(e.data);
  
}, false);

source.addEventListener('open', function(e) {
  // Connection was opened.
}, false);

source.addEventListener('error', function(e) {
  if (e.readyState == EventSource.CLOSED) {
    // Connection was closed.
  }
}, false);

