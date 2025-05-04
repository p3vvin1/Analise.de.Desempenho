class ListaLigada {
    Elemento inicio;

    int tamanho() {
        int count = 0;
        Elemento atual = inicio;
        while (atual != null) {
            count++;
            atual = atual.proximo;
        }
        return count;
    }

    void adicionar(int valor, int posicao) {
        if (posicao < 0 || posicao > tamanho()) {
            
            return;
        }

        Elemento novo = new Elemento(valor);
        if (posicao == 0 || inicio == null) {
            novo.proximo = inicio;
            inicio = novo;
            return;
        }

        Elemento atual = inicio;
        int contador = 0;
        while (contador < posicao - 1) {
            atual = atual.proximo;
            contador++;
        }
        novo.proximo = atual.proximo;
        atual.proximo = novo;
    }

    void remover(int valor) {
        Elemento atual = inicio;
        Elemento anterior = null;

        while (atual != null) {
            if (atual.valor == valor) {
                if (anterior == null) {
                    inicio = atual.proximo;
                } else {
                    anterior.proximo = atual.proximo;
                }
                return;
            }
            anterior = atual;
            atual = atual.proximo;
        }
    
    }

    void imprimir() {
        Elemento atual = inicio;
        while (atual != null) {
            System.out.print(atual.valor + " ");
            atual = atual.proximo;
        }
        System.out.println();
    }
}
