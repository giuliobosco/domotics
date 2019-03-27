/**
 * @author Giulio Bosco (giuliobva@gmail.com)
 * @version 0.1
 * @date 13/03/2018
 */
function getBrowser(){
	var ua= navigator.userAgent, tem,
		M= ua.match(/(opera|chrome|safari|firefox|msie|trident(?=\/))\/?\s*(\d+)/i) || [];
	if(/trident/i.test(M[1])){
		tem=  /\brv[ :]+(\d+)/g.exec(ua) || [];
		return 'IE '+(tem[1] || '');
	}
	if(M[1]=== 'Chrome'){
		tem= ua.match(/\b(OPR|Edge)\/(\d+)/);
		if(tem!= null) return tem.slice(1).join(' ').replace('OPR', 'Opera');
	}
	M= M[2]? [M[1], M[2]]: [navigator.appName, navigator.appVersion, '-?'];
	if((tem= ua.match(/version\/(\d+)/i))!= null) M.splice(1, 1, tem[1]);
	return M.join(' ');
}

function isMobile() {
	if( navigator.userAgent.match(/Android/i)
		|| navigator.userAgent.match(/webOS/i)
		|| navigator.userAgent.match(/iPhone/i)
		|| navigator.userAgent.match(/iPad/i)
		|| navigator.userAgent.match(/iPod/i)
		|| navigator.userAgent.match(/BlackBerry/i)
		|| navigator.userAgent.match(/Windows Phone/i)
	){
		return true;
	} else {
		return false;
	}
}


var b = (document.compatMode === "CSS1Compat") ? document.documentElement : document.body;

function getClientHeight() {
	return b.clientHeight * 1;
}

function getClientWidth() {
	return b.clientWidth * 1;
}

function getRandom(min,max) {
	return Math.floor(Math.random()*(max-min+1)+min);
}

function bitToBool(i) {
	if (i == 0) {
		return true;
	} else {
		return false;
	}
}