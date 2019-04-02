function hideNav(){
    document.getElementById("mainNav").style.visibility = "hidden";
    document.getElementById("triggerShow").style.visibility = "visible";
    document.getElementById("triggerHide").style.visibility = "hidden";
}

function showNav(){
    document.getElementById("mainNav").style.visibility = "visible";
    document.getElementById("triggerShow").style.visibility = "hidden";
    document.getElementById("triggerHide").style.visibility = "visible";
}