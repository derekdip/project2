

const getDistance =(n1, n2)=>{
    return Math.sqrt(Math.pow(n1.x-n2.x,2) + Math.pow(n1.y-n2.y,2));
}


const canvas = document.getElementById("visualGraph");
const ctx = canvas.getContext("2d");
canvas.width = width;
canvas.height = height;


for (let i = 0; i < list_of_nodes.length; i++) {//visualize the nodes and connections
    ctx.fillStyle = "red";
    ctx.beginPath();
    ctx.ellipse(list_of_nodes[i].x, list_of_nodes[i].y, 3, 3, 0, 0, Math.PI * 2); // draw the node
    // ctx.font = "16px serif";
    // ctx.fillText(list_of_nodes[i].val, list_of_nodes[i].x+3, list_of_nodes[i].y)
    // ctx.fill();
    ctx.fill();
    for (let j = 0; j < list_of_nodes.length; j++){
        if (i != j && getDistance(list_of_nodes[i], list_of_nodes[j]) < distance){ //if nodes are close enough, we connect them
            // connect the nodes
            ctx.beginPath();
            ctx.moveTo(list_of_nodes[i].x, list_of_nodes[i].y );
            ctx.lineTo(list_of_nodes[j].x, list_of_nodes[j].y );
            ctx.stroke();
            ctx.fillStyle = "black";
        }
    }
    ctx.font = "16px serif";
    ctx.fillText(list_of_nodes[i].val, list_of_nodes[i].x+3, list_of_nodes[i].y)
}
for(let i = 0; i <= canvas.height; i=i+50){
    ctx.font = "16px serif";
    ctx.beginPath();
    ctx.fillText(i,10, i)
}
for(let i = 0; i <= canvas.width; i=i+50){
    ctx.font = "16px serif";
    ctx.beginPath();
    ctx.fillText(i,i, 10)
}