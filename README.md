# Algoritmo Genético

## Implementação N-Rainhas

Implementado em Java o algoritmo genético aplicado ao caso das N-Rainhas.

Disciplina: Inteligência Artificial

## Algortimo Genético

![Diagrama Algoritmo Genético](https://icaroagostino.github.io/post/sbo/ga.png)

O algoritmo genético busca uma otimização global. Baseia-se nos mecanismos de seleção natural e de genética.
Basicamente é gerado uma população inicial de indivíduos, onde um indivíduo possui um gene proóprio.
A partir de dois indivíduos é gerado por cruzamento dois filhos, e a partir de um indivíduo apenas é gerado um mutate, que possuí genes modificados.
Cada indivíduo possui um fitness, um atributo que indica a qualidade do indivíduo.
Gerado os inidivíduos iniciais, ou os pais, os filhos e os mutantes, ambos passam por uma seleção de geração, onde uma quantidade n que é mais "capacitada" e uma quantidade m aleatória é selecionada para proxima geração.
A escolha aleatória, se dá pelo uso da "roleta viciada", na qual cada indivíduo recebe um pedaço proporcional à sua avaliação, depois é rodado a roleta e será selecionado o indivíduo sobre o qual ela parar.
O algoritmo chega em seu fim quando a condição de parada é satisfeita, condição essa que é definida pela implementação.

## Implementação Funções de BenchMark

CROSS-IN-TRAY FUNCTION

![CROSS-IN-TRAY FUNCTION](https://www.sfu.ca/~ssurjano/crossit.png)
![FUNCTION](https://www.sfu.ca/~ssurjano/crossit2.png)

SCHWEFEL FUNCTION

![SCHWEFEL FUNCTION FUNCTION](https://www.sfu.ca/~ssurjano/schwef.png)
![FUNCTION](https://www.sfu.ca/~ssurjano/schwef2.png)

PERM FUNCTION 0, D, BETA

![PERM FUNCTION 0, D, BETA](https://www.sfu.ca/~ssurjano/perm0db.png)
![FUNCTION](https://www.sfu.ca/~ssurjano/perm0db2.png)

Funções como essas implementadas estão disponíveis em: [Virtual Library of Simulation Experiments: Test Functions and Datasets](https://www.sfu.ca/~ssurjano/optimization.html)
