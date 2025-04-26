async function generatePlugin() {
    const pluginFile = document.getElementById('pluginSelect').value;
    const licenseKey = document.getElementById('licenseKey').value.trim();
    const serverIP = document.getElementById('serverIP').value.trim();

    if (!pluginFile || !licenseKey || !serverIP) {
        alert('Please fill out all fields.');
        return;
    }

    const response = await fetch('plugins/' + pluginFile);
    const blob = await response.blob();

    const zip = await JSZip.loadAsync(blob);
    zip.forEach(async (relativePath, file) => {
        if (!file.dir) {
            const content = await file.async('string');
            const newContent = content
                .replaceAll('{{LICENSE_KEY}}', licenseKey)
                .replaceAll('{{IP_ADDRESS}}', serverIP);
            zip.file(relativePath, newContent);
        }
    });

    const newBlob = await zip.generateAsync({ type: 'blob' });
    const link = document.createElement('a');
    link.href = URL.createObjectURL(newBlob);
    link.download = pluginFile.replace('.zip', '-Customized.zip');
    link.click();
}
