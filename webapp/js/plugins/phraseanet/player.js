flowplayer("a.phraseaPlayer", "js/plugins/phraseanet/flowplayer/flowplayer-3.2.15.swf", {
    // Le player se configure ici.
    plugins:  {
        controls:  {
            volume: false
        }
    }
});

$(function() {
$f("playerMulti", "js/plugins/phraseanet/flowplayer/flowplayer-3.2.15.swf", {
   clip: {
      // baseUrl: 'http://stream.flowplayer.org'
    }
    // playlist plugin
    }).playlist("#playlist");
});