const distance =90
const width = 1000
const height = 1000
const list_of_nodes = [
{val: 0, x: 697, y: 330},
{val: 1, x: 37, y: 80},
{val: 2, x: 220, y: 5},
{val: 3, x: 127, y: 42},
{val: 4, x: 641, y: 999},
{val: 5, x: 480, y: 470},
{val: 6, x: 404, y: 877},
{val: 7, x: 590, y: 884},
{val: 8, x: 180, y: 155},
{val: 9, x: 619, y: 691},
{val: 10, x: 952, y: 938},
{val: 11, x: 496, y: 225},
{val: 12, x: 435, y: 828},
{val: 13, x: 923, y: 299},
{val: 14, x: 909, y: 481},
{val: 15, x: 454, y: 429},
{val: 16, x: 17, y: 114},
{val: 17, x: 559, y: 203},
{val: 18, x: 340, y: 188},
{val: 19, x: 467, y: 958},
{val: 20, x: 317, y: 249},
{val: 21, x: 636, y: 71},
{val: 22, x: 649, y: 620},
{val: 23, x: 876, y: 660},
{val: 24, x: 435, y: 229},
{val: 25, x: 529, y: 431},
{val: 26, x: 103, y: 352},
{val: 27, x: 617, y: 755},
{val: 28, x: 696, y: 488},
{val: 29, x: 529, y: 759},
{val: 30, x: 141, y: 871},
{val: 31, x: 658, y: 417},
{val: 32, x: 964, y: 361},
{val: 33, x: 361, y: 109},
{val: 34, x: 307, y: 598},
{val: 35, x: 778, y: 150},
{val: 36, x: 211, y: 312},
{val: 37, x: 318, y: 85},
{val: 38, x: 883, y: 647},
{val: 39, x: 73, y: 370},
{val: 40, x: 46, y: 192},
{val: 41, x: 50, y: 207},
{val: 42, x: 557, y: 185},
{val: 43, x: 838, y: 882},
{val: 44, x: 169, y: 983},
{val: 45, x: 269, y: 972},
{val: 46, x: 706, y: 507},
{val: 47, x: 718, y: 154},
{val: 48, x: 939, y: 789},
{val: 49, x: 878, y: 154},
{val: 50, x: 126, y: 186},
{val: 51, x: 276, y: 603},
{val: 52, x: 540, y: 54},
{val: 53, x: 319, y: 155},
{val: 54, x: 294, y: 919},
{val: 55, x: 362, y: 270},
{val: 56, x: 423, y: 245},
{val: 57, x: 140, y: 449},
{val: 58, x: 495, y: 921},
{val: 59, x: 944, y: 605},
{val: 60, x: 4, y: 491},
{val: 61, x: 51, y: 422},
{val: 62, x: 385, y: 986},
{val: 63, x: 279, y: 258},
{val: 64, x: 811, y: 910},
{val: 65, x: 0, y: 165},
{val: 66, x: 836, y: 590},
{val: 67, x: 580, y: 65},
{val: 68, x: 545, y: 627},
{val: 69, x: 31, y: 6},
{val: 70, x: 140, y: 620},
{val: 71, x: 570, y: 648},
{val: 72, x: 757, y: 697},
{val: 73, x: 941, y: 853},
{val: 74, x: 902, y: 625},
{val: 75, x: 562, y: 312},
{val: 76, x: 99, y: 451},
{val: 77, x: 15, y: 624},
{val: 78, x: 201, y: 681},
{val: 79, x: 972, y: 162},
{val: 80, x: 363, y: 975},
{val: 81, x: 345, y: 339},
{val: 82, x: 861, y: 238},
{val: 83, x: 397, y: 610},
{val: 84, x: 750, y: 730},
{val: 85, x: 518, y: 655},
{val: 86, x: 907, y: 569},
{val: 87, x: 658, y: 705},
{val: 88, x: 93, y: 542},
{val: 89, x: 677, y: 910},
{val: 90, x: 539, y: 678},
{val: 91, x: 456, y: 963},
{val: 92, x: 979, y: 454},
{val: 93, x: 239, y: 965},
{val: 94, x: 204, y: 777},
{val: 95, x: 524, y: 613},
{val: 96, x: 981, y: 177},
{val: 97, x: 781, y: 402},
{val: 98, x: 605, y: 705},
{val: 99, x: 439, y: 443},
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
