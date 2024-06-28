const canvas = document.getElementById('canvas');
const ctx = canvas.getContext('2d');
let drawing = false;

// Resize canvas
function resizeCanvas() {
    canvas.width = canvas.offsetWidth;
    canvas.height = canvas.offsetHeight;
}

window.addEventListener('resize', resizeCanvas);
resizeCanvas();

// Mobile touch events
canvas.addEventListener('touchstart', startDrawing);
canvas.addEventListener('touchend', stopDrawing);
canvas.addEventListener('touchmove', draw);

// Desktop mouse events
canvas.addEventListener('mousedown', startDrawing);
canvas.addEventListener('mouseup', stopDrawing);
canvas.addEventListener('mousemove', draw);

document.addEventListener('mouseup', stopDrawing);
document.addEventListener('touchend', stopDrawing);

function startDrawing(e) {
    drawing = true;
    draw(e);
}

function stopDrawing(e) {
    e.preventDefault();
    drawing = false;
    ctx.beginPath();
    // sendDrawing();
}

function draw(e) {
    if (!drawing) return;

    e.preventDefault();
    ctx.lineWidth = 4.4;
    ctx.lineCap = 'round';
    ctx.strokeStyle = 'white';

    let x, y;
    if (e.touches) {
        x = e.touches[0].clientX - canvas.offsetLeft;
        y = e.touches[0].clientY - canvas.offsetTop;
    } else {
        x = e.clientX - canvas.offsetLeft;
        y = e.clientY - canvas.offsetTop;
    }

    ctx.lineTo(x, y);
    ctx.stroke();
    ctx.beginPath();
    ctx.moveTo(x, y);
}

function clearCanvas() {
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    document.getElementById('result').innerText = 'Draw something!';
}

function sendDrawing() {
    const image = canvas.toDataURL('image/png');
    console.log(image);

    fetch('/predict', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ image: image })
    })
    .then(response => response.text())
    .then(data => {
        document.getElementById('result').innerText = `The AI thinks you drew: ${data}`;
    })
    .catch(error => {
        console.error('Error:', error);
    });
}