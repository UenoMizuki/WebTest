import {judge,primeFactor} from "./testfunc.js"
//Canvas
var canvas = document.getElementById("myCanvas");
var ctx = canvas.getContext("2d");

/*
 * 赤い四角 ctx.beginPath(); ctx.rect(20, 40, 50, 50); ctx.fillStyle = "#FFFF00";
 * ctx.fill(); ctx.closePath(); var i=0;
 */
var framerate=1000/60;
var width=canvas.width;
var height=canvas.height;

var x=width/2,y=height/2;
var size=10;
var speed=2*Math.sqrt(2);
var rad=Math.PI/4;

var rightPressed = false;
var leftPressed = false;
var upPressed = false;
var downPressed = false;
var spacePressed=false;

document.addEventListener("keydown", keyDownHandler, false);
document.addEventListener("keyup", keyUpHandler, false);
document.onkeydown = pageMove;
var ar=new Array(32,33,34,35,36,37,38,39,40);
function pageMove()
{
	var key = event.keyCode-32;
	if(0<=key&&key<ar.length){
		return false;
	}
    return true;
}
var po=0;
function update(){
	move();
	draw();
	if(po++<1){
		primeFactor(1048576);
	}
	/*if(po++<1)
		judge(po);
	*/
}
function draw() {

    ctx.clearRect(0, 0, width, height);
    ctx.beginPath();
    ctx.rect(0, 0, width, height);
    ctx.fillStyle = "#DDDDFF";
    ctx.fill();
    ctx.closePath();
    
	ctx.beginPath();
    ctx.arc(x, y, size, 0, Math.PI*2);
    if(spacePressed){
        ctx.fillStyle = "#0095DD";
    }
    else{
        ctx.fillStyle = "#000000";
    }
    ctx.fill();
    ctx.closePath();
}
function move(){
	if(rightPressed) {
	    x += 5;
	}
	else if(leftPressed) {
	    x -= 5;
	}
	if(upPressed) {
	    y -= 5;
	}
	else if(downPressed) {
	    y += 5;
	}
	
	x+=speed*Math.cos(rad);
	y+=speed*Math.sin(rad);
	if(x<=size||x>=width-size){
		rad=Math.PI-rad;
	}
	if(y<=size||y>=height-size){
		rad*=-1;
	}
}
function keyDownHandler(e) {
    if(e.key == "Right" || e.key == "ArrowRight") {
        rightPressed = true;
    }
    else if(e.key == "Left" || e.key == "ArrowLeft") {
        leftPressed = true;
    }
    else if(e.key == "Up" || e.key == "ArrowUp") {
        upPressed = true;
    }
    else if(e.key == "Down" || e.key == "ArrowDown") {
        downPressed = true;
    }
    if(e.keyCode===32){
    	spacePressed=true;
    }
}

function keyUpHandler(e) {
    if(e.key == "Right" || e.key == "ArrowRight") {
        rightPressed = false;
    }
    else if(e.key == "Left" || e.key == "ArrowLeft") {
        leftPressed = false;
    }
    else if(e.key == "Up" || e.key == "ArrowUp") {
        upPressed = false;
    }
    else if(e.key == "Down" || e.key == "ArrowDown") {
        downPressed = false;
    }else if(e.keyCode === 32 ) {
        spacePressed = false;
    }
}

setInterval(update, framerate);