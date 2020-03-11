# thecatapi_test
Estrutura do projeto por pacotes

runTests ------ > Estão as classes que executam os testes assim como todas as validações e cenários de testes realizados.

bodyFactory ----> Estão as classes responsáveis pela construção do body juntamente com todas as suas variações conforme necessário.

suport ---------> Estão todas as classes que dão suporte ao projeto tais como: Manipulador de data, conexão com banco de dados, leitores de arquivos e etc, no caso deste projeto se encontra apenas a classe que realiza a leitura do arquivo de configuração.

arquivoConfig --> Se encontra o arquivo de configuração no qual pode ser preenchido com a url server, chaves de autenticação, usuários e etc, no caso deste projeto foi configurado apenas com url server, chaves de autenticação. Desta forma as informações ficam todas centralizados em apenas um local facilitando a manutenção do projeto.
