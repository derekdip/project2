const distance =90
const width = 1000
const height = 1000
const list_of_nodes = [
{val: 0, x: 414, y: 220},
{val: 1, x: 59, y: 625},
{val: 2, x: 942, y: 288},
{val: 3, x: 589, y: 212},
{val: 4, x: 985, y: 854},
{val: 5, x: 861, y: 417},
{val: 6, x: 3, y: 708},
{val: 7, x: 829, y: 193},
{val: 8, x: 680, y: 141},
{val: 9, x: 437, y: 802},
{val: 10, x: 368, y: 974},
{val: 11, x: 946, y: 426},
{val: 12, x: 504, y: 572},
{val: 13, x: 219, y: 216},
{val: 14, x: 295, y: 642},
{val: 15, x: 270, y: 339},
{val: 16, x: 386, y: 262},
{val: 17, x: 302, y: 333},
{val: 18, x: 873, y: 733},
{val: 19, x: 918, y: 760},
{val: 20, x: 928, y: 943},
{val: 21, x: 356, y: 314},
{val: 22, x: 0, y: 946},
{val: 23, x: 676, y: 251},
{val: 24, x: 814, y: 274},
{val: 25, x: 94, y: 554},
{val: 26, x: 162, y: 329},
{val: 27, x: 902, y: 178},
{val: 28, x: 419, y: 443},
{val: 29, x: 983, y: 48},
];

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
