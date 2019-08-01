function judge(money) {
  if (money > 5000) {
    document.write("お金持ち");
  } else if (money > 3000){
    document.write("普通");
  } else {
    document.write("貧乏");
  }
  console.log(money);
}
function primeFactor(num){
	for(var i=2;i*i<num;i++){
		while(num%i==0){
			num/=i;
			console.log(i);
		}
	}
	if(num!=1)console.log(num);
}
export { judge ,primeFactor}; 