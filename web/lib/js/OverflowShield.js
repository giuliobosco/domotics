/**
 * @author Giulio Bosco (giuliobva@gmail.com)
 * @version 1.0 (27.02.2018)
 * @requires jQuery 1.11.1 (jquery1.11.1.js)
 * @requires OverflowShield CSS (OverflowShield.css)
 * @requires Bootstrap v3.3.2
 */

function showShield(id) {
	$('#' + id).show();
	$('body').css('overflow', 'none');
}

function hideShield(id) {
	$('#' + id).hide();
}