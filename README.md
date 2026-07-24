# Mulher Amparada

O **Mulher Amparada** é um aplicativo de segurança e proteção desenvolvido especialmente para mulheres que desejam se sentir mais seguras em situações do dia a dia. O objetivo principal do aplicativo é oferecer suporte rápido, eficiente e acessível em momentos de risco, permitindo que a usuária consiga pedir ajuda de forma simples e imediata. Em um cenário onde muitas mulheres enfrentam situações de vulnerabilidade, ter uma ferramenta confiável pode fazer toda a diferença, e é exatamente isso que o Mulher Amparada busca proporcionar.

O aplicativo foi pensado para funcionar como um apoio digital em momentos críticos, permitindo acesso rápido a serviços essenciais como **Polícia (190)**, **SAMU (192)** e **Central de Atendimento à Mulher (180)**. Com apenas alguns toques, a usuária pode realizar ligações de emergência, reduzindo o tempo de reação em situações onde cada segundo é importante. Essa agilidade pode ser decisiva para evitar agravamentos e garantir assistência o mais rápido possível.

**Funções:**

Botão de Pânico
Botão de Pânico, com ligação ao 180 de forma direta no primeiro clique.

Proteção por Barulho
Ative a proteção, faça barulho alto e ele liga para o 180.

Balançar o Celular para Pedir Ajuda
Ative e, ao chacoalhar o celular, ele liga para o 180.

Área Protegida
Se estiver cadastrado no celular, com Biometric Prompt junto com Device Credential e autenticação weak, pode desbloquear essa área com impressão digital, rosto, PIN, padrão, senha e outros métodos.


🔐 Sistema Cripto (Segurança do App) = Antigo LocalStorage!:


Este sistema salva dados de forma segura usando criptografia nativa do Android.


🧠 Como funciona:


Quando você salva um dado no app, ele não fica em texto normal no celular. Ele é automaticamente criptografado antes de ser armazenado.


Isso significa que mesmo acessando os arquivos do dispositivo, os dados aparecem como códigos ilegíveis.


🔑 Tecnologia usada:



AES-256 (criptografia forte)

Android Keystore (chave protegida pelo sistema)

EncryptedSharedPreferences


⚙️ O que cada função faz:


salvar(chave, valor) → guarda o dado de forma criptografada

carregar(chave) → recupera o dado original

remover(chave) → apaga um dado específico

limparTudo() → remove todos os dados salvos


🔒 Segurança:


Os dados são protegidos por uma chave segura do próprio Android e não ficam visíveis diretamente no armazenamento do aparelho.

Calendário Menstrual
Registre como dói cada dia e, com isso, o aplicativo monta um calendário.

Calendário de eventos
Registra eventos da usuária quando ela precisar

Rotina
Sistema de pontos, com registro de comidas e bebidas boas e ruins, bem como a adição de registro de exercícios físicos fáceis, médios ou difíceis e contagem de tempo de cada um deles, além de sistema de nível e conquistas.

Mapa
Mostra um mapa da região da usuária

Diário
Usando criptografia, a usuária poderá anotar o que quiser. Com a senha, ficará seguro e também não some, pois estará guardado.

Relógio
Mostra o mapa do local atual, o país e outros dados, bem como o ano, semestre, bimestre, mês, quinzena, semana, dia, hora, minuto e segundo.

Além disso
Além de mostrar hora, minuto e segundo de todos os países.

Calculadora
A calculadora pode ser usada para cálculos rápidos do dia a dia.

Tarefas
O sistema permite categorizar tarefas em áreas como estudos, trabalho, pessoal e saúde.

As tarefas podem ser marcadas como concluídas para acompanhamento do progresso.

Todas as tarefas são salvas diretamente no navegador do usuário.

Os dados ficam armazenados localmente no dispositivo do usuário.

Gravador de voz
Usando uma activity (uma tela) em kotlin, é possivel ter um gravador de voz no app, sendo possível registrar evidências e provas, além do que a usuária quiser, sempre usando permissoes android e com o consentimento da usuária!

Meus arquivos
Dentro do app, ele só visualiza as pastas e ao clicar em um arquivo, abre um seletor de apps para executar/visualizar ele

Escolher ícone do app:
Essa função abre uma acitvity (tela) e poderá trocar o ícone do app!, até mesmo para um ícone de calculadora, disfarçando o app!

Desligar o celular:
Ao tocar neste botão, o aplicativo solicitará a permissão de Administrador do dispositivo, caso ela ainda não tenha sido concedida. Quando essa permissão estiver ativa, o aplicativo poderá bloquear imediatamente a tela do dispositivo.

Para desinstalar o aplicativo, primeiro será necessário desativar a permissão de Administrador do dispositivo. Em alguns aparelhos, também poderá ser necessário permitir Configurações restritas. Se essa opção estiver disponível, pressione e segure o ícone do aplicativo, toque em Informações do aplicativo, abra o menu de três pontos e ative Permitir configurações restritas.

Antes de conceder essa permissão, leia atentamente as informações exibidas pelo Android e só prossiga se compreender as funcionalidades e os efeitos dessa autorização.

Emergência
Saindo dessa área, existem botões que abrem o aplicativo nativo do telefone nos números 190, 191, 192 e 180.

Contatos de Confiança
Além dos contatos de confiança, clicando no primeiro botão você seleciona e salva o contato. O botão abaixo envia um pedido de ajuda para ele.

Widgets
Já saindo do app, temos os widgets.

Widget SOS
O primeiro é um widget de SOS que, quando clicado, abre o aplicativo de telefone com o número 180 já discado.

Widget de Localização
O segundo, o de localização, monta o link do Google Maps, e a usuária pode mandar para quem quiser.

Widget de Proteção Sonora
E o terceiro é apenas um acesso rápido para o serviço de proteção por palmas.

Compartilhamento Rápido de Localização
Além disso, existe um botão dentro do app que pega a localização atual, monta um link do Google Maps e já manda para o WhatsApp do 180, precisando apenas clicar no botão de enviar.

https://mulher-amparada.github.io/mulher-amparada-app/



**Como o código do app funciona?:**

então, esse app foi feito em kotlin

 é um app híbrido, ou seja, ele tem a MainActivity.kt carregando um layout de webview

para o app funcionar com as funções, ele chama uma classe chamada WebAppInterface.kt, com declaração dela na MainActivity e metodos expostos para js com anotação (@javascriptinterface), com isso, o javascript do site chama o metodo android + o nome do metodo, e essa classe faz a comunicação junto com as permissões concedidas para o sistema executar determinada função, exemplo:

botão de Pânico: primeiro é concedido a permissão de telefone, e o js chama o metodo android, que SÓ FUNCIONA COM ESSA PERMISSÃO, ou então pode ser que ele chama o telefone nativo com o respectivo número já discado, a classe faz a comunicação e pronto!

porem, para a proteção por barulho temos um servico com BACKGROUND + FOREGROUND chamado PalmaService.kt

e para o chacaolhar são métodos dentro da MainActivity que usam permissão de acelerômetro e chama o telefone nativo 

entenda que:

action_call: liga direto, so funciona com permissão 

action_dial: chama o telefone nativo com um número ja discado, precisando so apertar no botão 

Sobre a parte de enviar a localização: ele usa a permissão, monta um intent no Google maps e envia pelo whatsapp para o número do 180, precisando só apertar no botão de enviar

Sobre os contatos de confiança: ele chama o app nativo do telefone com a permissão ele salva o contato, e denovo monta link pelo google maps e envia

sobre a área protegida:

ela usa um código chamado (biometricPrompt), com a dependencia no build

e ela possui device_credential + biometric_weak

device_credential: é o pin/padrao/senha

biometric_weak: é rosto + impressão digital

quando ela é desbloqueada, a activity usa o media para tocar o som unlock.mp3, que foi feito no bandlab, 

e com ela, tem funções separadas assim:

funções na mesma tela: mesma activity, só troca o site

funções em outras telas: outras activitys, ou seja, ele sai dos sites e vao pra outras telas

da parte de EscolherIcone.kt, os icones sao activitys alias do manifest, puxados da pasta res/drawable

a FileActivity.kt usa a permissão especiak de acesso a todos os arquivos e o codigo puxa disso

a GravarActivity.kt usa a permissao de microfone + icones que sao xml em res/drawable, para a gravacao + animação de fade in e out

**APPS DIFERENTES!:**

**lembrando que, sobre esses apps, ja tem o código fonte dele dentro de um zip no site!:**

**Lembrando que o app de gerenciador de arquivos so visualiza arquivos  nao copia, nao exclui, nao move e nao renomeia, e ele também precisa desbloquear com biometria**

e o de comando de voz, também precisa desbloquear com biometria 

# Gerenciador de Arquivos do Mulher Amparada 📁💗

Conheça um gerenciador de arquivos pensado para ser leve, rápido e fácil de usar. Tudo foi desenvolvido para que você encontre seus documentos, fotos, vídeos e músicas sem complicação.

Com a permissão de acesso a todos os arquivos, o aplicativo consegue visualizar os arquivos armazenados no dispositivo e organizá-los de forma prática, facilitando a navegação pelas pastas.

Visualize suas fotos em uma experiência imersiva, ocupando praticamente toda a tela para aproveitar cada detalhe das suas imagens.

Ouça suas músicas utilizando o player integrado, sem precisar instalar outros aplicativos. A reprodução pode continuar em segundo plano, permitindo que você continue utilizando o celular enquanto escuta suas músicas favoritas.

Também é possível reproduzir vídeos diretamente pelo aplicativo, oferecendo uma forma simples de acessar seus conteúdos multimídia.

O Gerenciador de Arquivos do Mulher Amparada é totalmente gratuito e não exibe anúncios. Nada de propagandas interrompendo sua experiência ou ocupando espaço na tela.

O foco é oferecer velocidade, organização e uma interface limpa, para que você encontre exatamente o que procura com poucos toques.

Seja para abrir um documento, encontrar uma foto especial, ouvir uma música ou acessar um vídeo, o Gerenciador de Arquivos do Mulher Amparada reúne tudo em um único lugar, com praticidade e simplicidade.

# Assistente Inteligente do Mulher Amparada 🤖💗

        O Assistente Inteligente do Mulher Amparada permite controlar diversas funções do aplicativo utilizando comandos em linguagem natural, tornando o uso mais rápido e acessível.

        Com apenas um comando, é possível abrir aplicativos instalados no dispositivo sem precisar procurá-los manualmente.

        O assistente também pode ligar diretamente para a Central de Atendimento à Mulher (180), agilizando o acesso ao serviço em situações de necessidade.

        Caso autorizado, é possível enviar sua localização pelo WhatsApp para o número oficial do Ligue 180, facilitando o compartilhamento da sua posição.

        Você pode solicitar o envio de notificações personalizadas para lembrar compromissos, informações importantes ou qualquer mensagem desejada.

        Também existe a opção de simular uma notificação de download com barra de progresso, exibindo o andamento até a conclusão.

        Crie eventos no calendário do dispositivo utilizando comandos simples. O aplicativo abre a tela de criação do evento já preenchida para confirmação.

        Inicie timers por tempo determinado e receba uma notificação quando o tempo terminar. O timer também pode ser cancelado por comando.

        Consulte rapidamente os principais telefones de apoio e emergência disponíveis no Brasil diretamente pelo assistente.

        Visualize quantas vezes números de emergência, como 180, 181, 188, 190, 191, 192, 193 e 156 foram acionados, utilizando o histórico de chamadas do dispositivo quando autorizado.

        Abra fotos armazenadas no aparelho informando apenas o nome do arquivo.

        Também é possível abrir músicas e vídeos diretamente pelo nome do arquivo, utilizando os aplicativos compatíveis instalados no dispositivo.

        Verifique se o Bluetooth está ligado ou desligado por comando de voz ou texto.

        Abra rapidamente as configurações do Bluetooth quando desejar conectar ou gerenciar dispositivos.

        Consulte a lista de dispositivos Bluetooth pareados no aparelho de forma prática.

        Quando configurado como Administrador do Dispositivo, o assistente também pode bloquear imediatamente a tela do celular.

        Todas essas funções foram desenvolvidas para oferecer praticidade, rapidez e facilidade no dia a dia, mantendo uma experiência simples, intuitiva e totalmente gratuita, sem anúncios.
    


    
        Comandos do Assistente Inteligente 🤖💗

        O Assistente Inteligente reconhece comandos em linguagem natural. Veja alguns exemplos:

        📱 Aplicativos
        • Abrir WhatsApp
        • Abrir Instagram
        • Abrir Configurações

        📞 Emergência
        • Ligar para 180
        • Ligar para o 180

        📍 Localização
        • Enviar minha localização para o 180
        • Mandar minha localização para o 180

        🔔 Notificações
        • Me mande uma notificação Estou chegando.
        • Me mande uma notificação Lembrete importante.

        ⬇️ Download fictício
        
        • me envie uma notificação de download ficticio
        • Me envie uma notificação de download ficticio
        • me envie uma notificação de download fictício
                • Me envie uma notificação de download fictício
        • me mande uma notificação de download fictício
                • Me mande uma notificação de download fictício
        • me mande uma notificação de download ficticio
                • Me mande uma notificação de download ficticio

        📅 Calendário
        • Criar evento Reunião
        • Crie um evento Consulta médica
        • Adicionar evento Aniversário
        • Marcar compromisso Academia

        ⏲️ Timer
        • Inicie um timer de 5 minutos
        • Inicie um timer de 30 minutos
        • Cancelar timer

        ☎️ Telefones de apoio
        • Quais são os meus apoios
        • Quais sao os meus apoios
        • Meus apoios

        📋 Histórico de ajuda
        • Quantas vezes liguei para pedir ajuda
        • Ligações de ajuda
        • Histórico de ajuda

        🖼️ Fotos
        • Abrir foto viagem.jpg
        • Abrir foto imagem.png

        🎵 Músicas
        • Abrir música musica.mp3

        🎬 Vídeos
        • Abrir vídeo video.mp4

        📶 Bluetooth
        • Bluetooth está ligado
        • Bluetooth esta ligado
        • Abrir Bluetooth
        • Configurações do Bluetooth
        • Listar dispositivos Bluetooth
        • Quais dispositivos Bluetooth
        • Dispositivos pareados

        🔒 Bloqueio do aparelho
        • Bloquear celular
        • Bloquear aparelho

        🔍 Pesquisa
        • Pesquisar + termo a ser pesquisado (e ai ele abre o navegador com a pesquisa feita no google)
        



# Sobre a permissão de sms:

é um avanço, pois é uma permissão a menos pro app!, deixei esses registros dessa permissão nesse repositório porque pode ser que precise... (esse aviso serve apenas pro app do Mulher Amparada!)
       
# Livro Cartas Datilografadas:

Nessa história,uma jovem garota chamada Isis Almeida estava em seu momento de lazer, até que em algumas horas,ela ficou impressionada por algo... .

https://clubedeautores.com.br/livro/cartas-datilografadas

(Sim, eu fiz ele em 2023, com 12 anos de idade..., e sou fã de undertale desde 2020!)

# Sobre o desenvolvedor:

**Aviso:** esse Repositório é apenas o local onde você baixa o app, ele não é o proprio app em si!, essas funções só funcionarão quando você baixar, instalar e usar o app!

**Aviso:** Sempre considerem que um menino de 15 anos, autista nível 1 e um menino é o próprio desenvolvedor

**Dos meus 10 aos 15 anos⬇️**

O que eu passei na Escola Parque Jardim Helena:
          
ai ja no inicio do sexto ano, criei amizade com a ****

nos jogavamos

e ela falava muito algo e depois olhava pra mim e falava ("né samuel?"), e eu falava, "é!..."

so que um dia, ela achou que eu tinha falado palavra, que era se eu nao me engano "vai tomar no..."

e ai eu tinha terminado a amizade com ela para ficar com a ****

so que ai, eu nao perce****, maa segundo o que minha mae dizia: "ela me usava so para copiar licao minha e ganhar nota emcima de mim"

e segundo o que minha mae contou pra mim: "agora ela ta sofrendo porque nao tem ninguem pra ajudar ela"

a **** chamou ela e tudo, ate a mae

so que ela me ajudou com a **** e como interagir


Depois, a aluna **** chegou

ela falava muito palavrão, mas segundo eu (vejo de acordo com o contato que tive com ela, como sendo uma pessoa boa e de caráter adequado), e tambem eu via como ela agia e falava, entao posso dizer isso

so que ai, estávamos na sala de aula, e ela quis em uma interação que eu riscasse a sobrancelha para o grupo se incluir,

e ai o ambiente me pressionou, porque nao conseguia falar nao e nao queria perder a amizade 

e ai fiz o erro de desobedecer minha mae e riscar de fato

por isso, me senti coagido

e ai deu problema com a direção e coordenação com a ****, e a mae dela pode ser que nao comprou o presente de aniversario dela (segundo ela)

Coisas boas:

e ai tinha a professora **** e outra que nao lembro (agora no dia 15.06.2026 lembrei que e a ****)

a **** sempre deixava quando eu terminava a licao, usar o celular

entao, um dia eu tava jogando papo fora  com o **** (autista nivel 3), ate que

ele tentou colocar o dedo dele no meu ânus 

e ai eu nao deixei o que ele estava fazendo simplesmente bloqueando essa atitude com mimhas duas mãos 

sem contar daquela vez que eu emprestei um fone verde claro pra ele

e ele quebrou

contei ora minha mae

e no mesmo dia ela comprou um fone roxo bem meljor pra mim

e acalmoy  a mae do ****, a ****

w o **** tava todo preocupado 

mas ela, a minha mae, acalmou eles...

mas...

chamaram a mae do ****, e advertiu ela e se fazer denovo, ele vai ser expulsop

porem, varias vezes eu desligava o computador dele (porem, ele desligava o meu tambem), e isso foi um erro meu. mas eu fazia isso porque queria interagir com ele, e brincar com ele (eu nao desligava de fato, so ameaçava)

mas ai a minha mae me deu um algodao doce e entreguei pra ele e pedi desculpas 

passou se um tempo

e ai depois, eu conheci um menino chamado ****

o **** foi chamado a mae dele, com a ****, a conselheira tutelar, foi advertida e se acontecer seria expulso e falaram pra minha mae que se acontecer denovo pode abrir um boletim de ocorrencia

como eu era autista, eu ficava enchebdo o saco dele

so com 11 anos deu o diagnostico de autismo

ate que ele falou palavras obscenas e escreveu no computador da escola algo muito errado:

depois ele se consertou quando chamaram o conselho tutelar

e ai ele virou meu amigo escondido

nos brincavamos de peteca e so naqueles momentos eu ria e sorria naquela escola

eu via isso 



Só que aí minha vida virou um completo inferno quando conheci uma garota chamada ****

História da ****:

No começo, eu vi ela.

Ela era muito bonita, com cabelos cacheados no bicicletário.

Só que aí… foi aí que tudo começou a complicar.

Eu passei por tudo sozinho.

Mas, com o tempo, eu também entendo que a verdade é que eu não pedi ajuda.

No começo, vi ela novamente, muito bonita, em uma tarde perto das árvores e do bicicletário.

Só que nos outros dias…

Eu fiz a burrada de começar a seguir ela.

Eu não sabia que era errado na época, mas minha intenção era só tentar pedir amizade.

Eu seguia ela para perguntar, sem idealização, só de forma simples:
“Podemos ser amigos?”

Mas ela fugia.

E ela dizia coisas como:

“Para de seguir os outros, licença.”

E em outro momento, com raiva:

“Samuel, para de me seguir!”

Aí eu fazia testes pra ter certeza… e era isso mesmo.

Passaram-se os dias.

Um dia o grêmio da escola me entrevistou e perguntou:

“Samuel, de quem você gosta?”

E eu respondi que gostava da ****.

Ela comentou:

“Ah, ele é legal.”

Depois de um tempo, no intervalo do almoço, tentei sentar perto dela.

Mas ela se afastou.

Mais tarde, entrei na mesma eletiva que ela, sem esperar por isso: “O Mundo em Movimento”, com o professor **** e o professor ****.

Nessa disciplina, criamos um robô que se movia. Eu fiquei responsável pela parte de movimento com o professor ****.

Em um momento, enquanto trabalhava no robô, machuquei o dedo.

E a **** reagiu com raiva dizendo:

“Ah, para de ser besta!”

Eu olhei para ela e depois virei o rosto.

O **** me consolou dizendo:

“Fica triste não, ela é assim mesmo.”

Com o tempo, eu senti vontade de pedir desculpas por ter seguido ela.

Aí vieram várias decisões erradas.

Escrevi uma carta com a **** pedindo desculpas. Ela me incentivou a entregar.

No terceiro intervalo, fui até o refeitório e entreguei a carta.

Ela rejeitou de forma brusca, como se fosse algo muito incômodo para ela.

Depois, em desespero, pedi para o **** e a **** entregarem também.

Ela amassou a carta.

Eles leram e disseram:

“Ela desprezou isso?”

“Samuel, ela realmente não te merece…”

“Esquece ela.”

Depois de um tempo, tive outra atitude errada: mandei mensagem no WhatsApp pedindo desculpas.

Consegui o contato com ajuda do professor ****.

Expliquei que minha intenção era só amizade.

Ela respondeu dizendo que entendia que não era certo o que ela disse, mas que acreditava que eu queria mais do que amizade, e pediu para não ter proximidade nem contato.

Naquele ponto, eu já não sentia mais o mesmo que antes. Eu só queria encerrar a situação.

Com o tempo, eu passei a respeitar o limite dela durante o ano todo.

Mas o que aconteceu foi que ela continuou demonstrando raiva e desconforto quando me via durante todo o ano, (fazendo cara de raiva pra mim), mesmo eu não fazendo nada.

Eu não estou dizendo que isso era algo direcionado, apenas relatando o que eu percebo.

Em outro momento, acabei me aproximando das amigas dela, o que também foi um erro.

Teve um dia em que sentei perto delas, mas elas saíram e me deixaram sozinho.

Em 2025, tentei novamente contato de forma indireta, usando o **** para falar com ela e mencionando meu aniversário.

Eu também já tinha feito um texto e um design para o aniversário dela.

No meu aniversário, ela respondeu apenas:

“Não quero saber.”

Em outro momento, pedi para uma vice-diretora intermediar uma conversa.

Ela disse que, se eu quisesse falar com ela, tudo bem.

Em outra ocasião, a diretora **** interrompeu uma conversa em que eu tentaria explicar mais coisas, incluindo minha dor emocional. Provavelmente foi para proteger o ambiente escolar.

Eu disse apenas:

“Você já sabe de tudo o que aconteceu, por isso peço desculpas.”

E ela aceitou.

Também desabafei com a professora **** mas me machuquei mais ainda...

Em alguns momentos, senti que eu me dedicava muito às pessoas e não recebia o mesmo em troca, mas depois percebi que isso não é necessariamente verdade.

Teve um episódio em que ela fez um gesto ofensivo em minha direção (dedo do meio), e a mãe dela foi chamada e ela foi advertida.


Importante: eu não estou mais naquela escola e não tenho mais contato com ela. Essa fase terminou.

Eu entendo que o limite dela de não querer contato é válido, assim como o meu limite de respeito também é válido.

Eu nunca quis obrigar ninguém a gostar de mim ou ter proximidade comigo.

Meu limite é apenas não ser tratado com desrespeito básico.

Se houver erro, eu entendo. O valor de uma pessoa não depende dessas situações.

E em caso de conflito, eu tento compreender sem precisar que a outra pessoa goste de mim (e no caso da ****, ela fazia cara de raiva quando olhava pra mim eu tava dizendo...)


a **** me explorava em licao me deixava fazer todos os trabalhos da sala sozinho

a **** chamou ela e advertiu ela porque fazer muita licao me desregulava

acredito eu e minha mae que eu quase tive uma corda neural por causa disso

perdi mais de ≈ 115gb de dados

mais de 20 apps que sao sistemas

agora reconstrui e tenho 2 apps e 17gb de arquivos

e ai teve umas situações que...

o **** fazia brincadeiras como raps me elogiando

gritando no meu ouvido

fazendo ironias tipo

ah, voce tirou a mao dai porque eu sou bandido ne!, to sabendo...

o **** foi advertido pra nao fazer e parar com isso senao iria chamar a mae dele

antes tinha a **** que, eu fui ajudar a professora dora a dar nota

e ai eu ia corrijindo

tava errado eu

e a **** estava com raiva e falou pra focar no meu

teve outro dia que ela tava falando muito e nao deixava os outros falar

e ai eu estava chamando o professor

eu meio errado, **** meio certa e professor errado

e ai, ela falou grosso: "ce da para esperar eu terminar de falar..."

e ai eu falei: "e eu te perguntei alguma coisa"

ela ficou triste, eu tambem ja tava triste por fazer isso e por ela ser grossa comigo

e ai o professor chamou os dois e explicou os porques

e mesmo assim ela foi grossa

e eu fui também falando que, oxi eu tava no meu momento de chamar o professor, se voce jao fosse grossa eu nao faria isso

mas eu sei, eu to um pouco errado

e a **** foi advertida pela ****, falou pra ela ter educação

sem contar que no whatsapp, eu tinha pedido pra ela (sim, outros alunos também pediam pelo mesmo numero, isso era apenas a logistica daquela escola...), eu tinha pedido e ela foi muito grossa, que tava sobrecarregada e etc


e sobre o ****

tinha uma situacao em que

eu estava na sala de informatica

e ele numa mesa virado pra tras e eu em outra

e ele virou pra tras e me chamou de:

"cu aberto" mais de 10 vezes no minimo

e ele me zoava falando

ah, quando eu pegar ele ele vai falar

ahhhhhhhwww

e levantava as maos pra cima

e ai eu avisava pra professora e nao fazia nada

e ai eu ameacei de ir na ****, a vice diretora

e ai a **** se meteu e disse:

"oxi, mas ele nao fez nada"

uhum, sei...

e ai chegamos na sala

ele ameacou dizendo que vai machucar a minha mae

e eu disse que ele vai se ver no conselho

e ai a **** se meteu denovo dizendo:

"mas sua mae nao esta 24 horas por dia por voce!"

sendo que ela vai na escola sim, e ela mesma falou que se precisar, fica dia e noite

e eu me defendo

ela foi na ****, denovo nao fez nada e so registrou

e ai denovo um dia ele me gritou na aula

Cu abeeeerto

e ai teve outra vez que ele falou

sua mae veio aqui, deu mo b.o

eh, fiotw vai falar nada nao

e ai, fizeram boletim de ocorrencia contra o **** e o ****


e ai o **** teve uma vez que eu estava na frente da lousa e ele nao conseguia copiar a licao

nota: ele que tinha que levantar, nao eu que tinha que sair

e ele falou: e, samuel, cu aberto, 

quando ele falou cu aberto eu entendi

so que ja tava b.o da delegacia,o relatório meu que escrevi, o do estela e da escola do parque

e ai minha mae veio na escola conversar com o coordenador

e ai a da tarde, porque nao tinha a de manha

me falou para nao ter medo de chamar a gente quando acontecer essas coisas

so que na hora, o **** falou que meus pais iriam bater nele (engraçado ne, ele que ameacou que iria machucar meus pais...)

ou seja, ele deu falso testemunho

e ai o ****, a vice diretora **** e o coordenador **** falaram para nao ter medo e bla bla bla

minha mae foi denovo, e o coordenador **** falou:

"ue, voce e cristã, porque você ta fazendo isso?"

e ai na delegacia o delegado ficou com raiva do que fizeram comigo e falou: "e mae, voce ta certa em denunciar!!!"

o **** levou uma suspensão, ta na vara da infancia

tava andando na rua uns dias e veio o ****, ele cantava um funk alto so pra provocar e gritou um grito no ouvido da minha mae, bem afeminado (mantenho meu respeito)

e ai a minha mae chamou ele

e ele disse:

"por causa desse bobao, desse mentiroso, me arrumou mo b.o esse mentiroso"

e ai eu gritei bem alto:

"vai parar ou vai ficar se humilhando de graca?, grito nao impoe autoridade, abaixa o tom de voz para falar comigo!"

vi essa frase no instagram, ele ate saiu com vergonha e medo...


e ai por ultimo

teve uma situacao que eu estava fazendo uma pergunta pro professor de matemática ****

e perguntei o que e cobranca adicional

ele falou que e desconto

e alguém la do fundo falou

"ah, cala a boca!!!"

e ai na hora na proxima aula a de artes fui na voce direcao falar com a ****

ela falou que na hora e para falar com o professor

porque como vai saber quem foi

e ai, a **** o **** e o **** foram na sala falar de respeito e ser alguém na vida...

e com isso, me trocaram pro dom pedro, teve ocorridos mas la e melhor


E voces tem que entender que NAO TEM mais essas conclusões absolutas, as vezes foi contexto, falta de humor ou sei la...

ta certo que a **** agiu, mas ela me deixou sem apoio tambem, e nao agiu tao corretamente assim...

    
  O que eu passei na Escola Dom Pedro 1° de São Miguel:
    
  Tudo o que o **** fez:

Um dia, o ****, do 1º ano B da Escola Estadual Dom Pedro I, na aula de Educação Física, quando eu estava na rampa indo para a sala, ele falou pra mim:

“Oi bebê, cê tá solteiro?”

Eu fiquei quieto, tímido, desconfortável e me senti invadido.


---

Dia 06/04/2026:

Na aula de Geografia, ele me pediu todos os resumos.

Quando eu não dei, ele falou:

“Moleque desgraçado.”

Eu respondi:

“O que você falou?”

Ele então disse:

“Moleque engraçado.”

Já falei pra professora e contei tudo pra ela.

Ela respondeu:

“É, não empresta.”

Depois ela disse:

“Pode deixar que vou ficar de olho nele.”

Só isso que ela fez.


---

Na aula de Artes, depois da aula de Geografia, outro menino (não era o ****), um de camisa branca e calça jeans cinza, me pediu meu caderno de Artes.

Eu disse que não.


---

Outra coisa:

Às vezes tem tanta lição que os professores passam que eu me sinto sobrecarregado e até desregulado.


---

A professora **** me fez esperar 20 minutos para ir ao banheiro no dia 27/04/2026, sendo que eu tenho autismo e deveria ter prioridade.

Ela também passa muitos resumos e não explica a matéria direito.

Ela foi um pouco grossa comigo quando foi dar visto no meu caderno, na pesquisa dos aquíferos, dizendo:

“Samuel, espera eu dar nota para eles, eles estão precisando mais que você.”


---

O professor ****, de Educação Financeira, também foi grosso comigo quando respondi uma pergunta, dizendo:

“Ah, me complica...”


---

Dia 28/04/2026:

Tentaram abrir a porta do banheiro enquanto eu estava usando, e eu tenho como provar.

Além disso, o professor de Educação Financeira costuma ser grosso com os alunos (não só comigo).

Ele me deu 5 em um trabalho que me esforcei muito para fazer e 7 na prova, dizendo que eu não escrevo bem.

---

A professora **** me deu 9 em Geografia, dizendo que meu caderno estava “bagunçado”.

Eu fiz cerca de 20 resumos, e eu sentia dificuldade para aprender com o método utilizado, porque havia muitos resumos e poucas explicações diretas e ela manda os alunos fazerem resumos para aprender sozinhos.

E tambem, eu tava ajudando a turma que quando nao carrega o speak, pode ser bloqueio da cloudflare, e ai a professora **** de ingles, falou: amor, eu mexo com isso 8 anos, e eu tambem programo desde 2025, e escrevo e mexo com digital desde 3,5 anos (so para vocês saberem, nao quero ser melhor que ninguém, seriao...), mas a maior questao e nao usar dados moveis e sim o Wi-Fi da escola, ai sim funciona na maioria das vezes...

---

Contexto:
Na prova paulista, tinha feito a prova paulista normal (física), sendo que eu tenho autismo nível 1 e deveria ter aplicado a prova adaptada da sala do futuro, nao a física
    
E o diretor ****, viu que todos os alunos estavam bagunçando e impediu que todos os alunos (ate os que estavam quietos, e eu estava quieto e tenho autismo), nao poderiam sair mais cedo da sala...
    
A minha mãe foi la na escola, mas falaram que eles erraram, fizeram uma reuniao e não aplicaram a prova adaptada da sala do futuro pra mim, e sim a normal, e ai eles queriam que eu fizesse as duas provas normais e as duas provas adaptadas
        
A minha mae chegou lá e o professor **** estava lá, e falando com o diretor **** e explicou a situação pra ele, e falou das minhas faltas que nao foram regularizdas e da punição coletiva
    
E falou também do projeto PEI, falando com o professor ****, ele mandou esperar 20 minutos, chegou a professora **** e pediu para falar com o coordenador ****, e chamaram a professora ****, ela disse que isso foi discutido ontem (devia ter feito antes da prova, nao no dia da primeira prova), e mesmo assim ela nao aplicou a prova (ela disse que e porque ela tinha 30 alunos para dar conta...)
    
E a minha mãe falou que precisa da PEI, e ela pegou o papelzinho com a. adaptação de todos os professores, e ja estava para fazer desde o primeiro bimestre ate nesse dia, e ela só entregou de uma professora sendo que tinha 2 bimestres para ter feito isso 
    
E veio o ****, correu e corrigiu minhas faltas 
    
e ai veio a **** e falou (ah, mas na outra escola) 
    
e ela chamou o supervisor ****, e ele mandou aplicar a prova adaptada + a prova normal (e ele falou, voce nao manda aqui, quem manda aqui sou eu, e falou que se nao fazer vai ser prejudicado...) 
    
Ou seja, eu iria fazer 4 provas, e iria um dia extra nesse frio para fazer as 2 provas adaptadas por causa de um erro da escola...
    
e ele estava com o crachá virado 
     
e o coordenador **** ja falou (ah, mas mae, ele e o samuel, ele vai se dar bem...)
     
e ela, a professora de itinerância ****, falou  que eu nao necessito de atendimento especial
     
Dia 23/06/2026:

Contexto:

Era prova de saresp dos terceiros anos...

entao os terceiros anos ocuparam a sala dos primeiros anos

entao ficamos no teatro, assistindo o filme interestelar

e a Vice-diretora **** falou que era para me buscar as 12:20 (horário normal), sendo que os alunos sairam 10:20

por causa disso, fiquei ≈02:20 minutos esperando...

**417 Pensamentos de se matar que eu tive ate entao (aproximadamente...)
       