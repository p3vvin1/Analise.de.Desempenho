#definindo o que é um Elemento (nó) 
class Elemento:
    def __init__(self, valor):
        self.valor = valor
        self.proximo = None
#criando a lista ligada 
class ListaLigada:
    def __init__(self):
        self.inicio = None
#Função que adiciona elementos 
    def adicionar(self, valor, posicao):
        novo = Elemento(valor)
        #se for no inicio da lista 
        if posicao <= 0 or self.inicio is None:
            novo.proximo = self.inicio
            self.inicio = novo
            return
        #se for no meio ou no final
        atual = self.inicio
        contador = 0
        while atual.proximo and contador < posicao - 1:
            atual = atual.proximo
            contador += 1

        novo.proximo = atual.proximo
        atual.proximo = novo
        #função que remove elemento
    def remover(self, valor):
        atual = self.inicio
        anterior = None
        while atual:
        #se for o primeiro elemento  
            if atual.valor == valor:
                if anterior is None:
                    self.inicio = atual.proximo
        #se for no meio ou no fim         
                else:
                    anterior.proximo = atual.proximo
                return
            anterior = atual
            atual = atual.proximo
        #função que imprime a lista 
    def imprimir(self):
        atual = self.inicio
        valores = []
        #começa do primeiro elemento
        while atual:
            valores.append(str(atual.valor))
            atual = atual.proximo
        print(' '.join(valores))
        #função que lê o arquivo 
def ler_arquivo(nome_arquivo):
    with open(nome_arquivo, 'r', encoding='utf-8-sig') as arquivo:
        linhas = arquivo.readlines()
    return [linha.strip() for linha in linhas if linha.strip()]
        #função que executa os comandos 
def executar_comandos(caminho_arquivo):
    linhas = ler_arquivo(caminho_arquivo)

    # iniciar a lista
    numeros_iniciais = list(map(int, linhas[0].split()))
    quantidade_acoes = int(linhas[1])
    comandos = linhas[2:]

    lista = ListaLigada()

    # Inserir os números iniciais na ordem correta
    for numero in reversed(numeros_iniciais):
        lista.adicionar(numero, 0)

    # executar comandos
    for comando in comandos:
        partes = comando.split()

        if partes[0] == 'A':
            valor = int(partes[1])
            posicao = int(partes[2])
            lista.adicionar(valor, posicao)

        elif partes[0] == 'R':
            valor = int(partes[1])
            lista.remover(valor)

        elif partes[0] == 'P':
            lista.imprimir()

if __name__ == "__main__":
    executar_comandos('arq.txt')  # Coloque aqui o nome correto do seu arquivo .txt convertido!
