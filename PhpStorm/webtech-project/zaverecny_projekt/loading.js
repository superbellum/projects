onmessage = function(MessageEvent)
{
    if (MessageEvent.data === "startWorker")
    {
        postMessage({messageType: "start"});
        setTimeout(function () {

            postMessage({ messageType: "end" });

            }, 500);
    }

};