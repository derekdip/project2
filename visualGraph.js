const distance =90
const width = 1000
const height = 1000
const list_of_nodes = [
{val: 0, x: 540, y: 801},
{val: 1, x: 916, y: 872},
{val: 2, x: 671, y: 909},
{val: 3, x: 894, y: 958},
{val: 4, x: 233, y: 657},
{val: 5, x: 277, y: 974},
{val: 6, x: 592, y: 132},
{val: 7, x: 418, y: 766},
{val: 8, x: 829, y: 343},
{val: 9, x: 555, y: 56},
{val: 10, x: 884, y: 230},
{val: 11, x: 122, y: 705},
{val: 12, x: 802, y: 44},
{val: 13, x: 122, y: 859},
{val: 14, x: 443, y: 267},
{val: 15, x: 994, y: 849},
{val: 16, x: 127, y: 477},
{val: 17, x: 672, y: 675},
{val: 18, x: 430, y: 476},
{val: 19, x: 876, y: 127},
{val: 20, x: 484, y: 795},
{val: 21, x: 7, y: 130},
{val: 22, x: 941, y: 200},
{val: 23, x: 422, y: 680},
{val: 24, x: 1, y: 142},
{val: 25, x: 274, y: 836},
{val: 26, x: 711, y: 187},
{val: 27, x: 534, y: 571},
{val: 28, x: 101, y: 342},
{val: 29, x: 311, y: 954},
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
