const http = require('http');
const fs = require('fs');

const server = http.createServer((req, res) => {
    const mapa = [];
    mapa['/'] = '<h1>Casa</h1>';

    const arquivo = __dirname + '\\app' + req.url;
    //const arquivo = 'C:\\Users\\Fernando Frazão\\Documents\\sistemas\\evbem\\web\\app\\index.html';

    console.log(arquivo);

    fs.exists(arquivo, (exists) => {
        if (exists) {
            res.setHeader('Content-Type', 'text/html');
            const conteudo = fs.createReadStream(arquivo, 'utf8');
            console.log('servindo  ', arquivo, conteudo);
            res.write(conteudo);
            res.end();
        } else {
            res.end(mapa[req.url] || '<h1 color="red">Endere&ccedil;o inexistente!</h1>');
        }
    });

});

server.listen(3000, 'localhost', () => console.log('servidor iniciado!'));