const fs = require('fs');


class Elemento {
    constructor(valor) {
        this.valor = valor;
        this.proximo = null;
    }
}


class ListaLigada {
    constructor() {
        this.inicio = null;
    }

    adicionar(valor, posicao) {
        const novo = new Elemento(valor);

        if (posicao <= 0 || this.inicio === null) {
            novo.proximo = this.inicio;
            this.inicio = novo;
            return true;
        }

        let atual = this.inicio;
        let contador = 0;

        while (atual.proximo !== null && contador < posicao - 1) {
            atual = atual.proximo;
            contador++;
        }

        if (contador < posicao - 1) {
            
            return false;
        }

        novo.proximo = atual.proximo;
        atual.proximo = novo;
        return true;
    }

    remover(valor) {
        let atual = this.inicio;
        let anterior = null;

        while (atual !== null) {
            if (atual.valor === valor) {
                if (anterior === null) {
                    this.inicio = atual.proximo;
                } else {
                    anterior.proximo = atual.proximo;
                }
                return true;
            }
            anterior = atual;
            atual = atual.proximo;
        }
        
        return false;
    }

    imprimir() {
        let atual = this.inicio;
        const valores = [];
        while (atual !== null) {
            valores.push(atual.valor);
            atual = atual.proximo;
        }
        console.log(valores.join(' '));
    }
}


function executarComandos(caminhoArquivo) {
    const linhas = fs.readFileSync(caminhoArquivo, 'utf8')
        .split('\n')
        .map(l => l.trim())
        .filter(l => l.length > 0);

    const numerosIniciais = linhas[0].split(' ').map(Number);
    const quantidadeAcoes = parseInt(linhas[1]);
    const comandos = linhas.slice(2);

    const lista = new ListaLigada();

    
    for (let i = numerosIniciais.length - 1; i >= 0; i--) {
        lista.adicionar(numerosIniciais[i], 0);
    }

    
    for (const comando of comandos) {
        const partes = comando.split(' ');
        const acao = partes[0];

        if (acao === 'A') {
            const valor = parseInt(partes[1]);
            const posicao = parseInt(partes[2]);
            if (!lista.adicionar(valor, posicao)) {
            
                continue;
            }
        } else if (acao === 'R') {
            const valor = parseInt(partes[1]);
            lista.remover(valor); 
        } else if (acao === 'P') {
            lista.imprimir();
        }
    }
}

executarComandos('arq.txt');
