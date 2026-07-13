# Mulher Amparada

**Aviso:** esse Repositório é apenas o local onde você baixa o app, ele não é o proprio app em si!, essas funções só funcionarão quando você baixar, instalar e usar o app!

**Aviso:** Sempre considerem que um menino de 15 anos, autista nível 1 e um menino é o próprio desenvolvedor

**Aviso:** Como o desenvolvedor tem 15 anos, ele não poderia lançar esse app na play store porque precisa ter 18 anos, mas no futuro possívelmente lançarei na play store... (e nao vou postar até fazer 18 anos...), e lembrando, que as páginas desse site estão na internet, mas as paginas do webView do app não estão, são paginas internas da pasta assets...

O **Mulher Amparada** é um aplicativo de segurança e proteção desenvolvido especialmente para mulheres que desejam se sentir mais seguras em situações do dia a dia. O objetivo principal do aplicativo é oferecer suporte rápido, eficiente e acessível em momentos de risco, permitindo que a usuária consiga pedir ajuda de forma simples e imediata. Em um cenário onde muitas mulheres enfrentam situações de vulnerabilidade, ter uma ferramenta confiável pode fazer toda a diferença, e é exatamente isso que o Mulher Amparada busca proporcionar.

O aplicativo foi pensado para funcionar como um apoio digital em momentos críticos, permitindo acesso rápido a serviços essenciais como **Polícia (190)**, **SAMU (192)** e **Central de Atendimento à Mulher (180)**. Com apenas alguns toques, a usuária pode realizar ligações de emergência, reduzindo o tempo de reação em situações onde cada segundo é importante. Essa agilidade pode ser decisiva para evitar agravamentos e garantir assistência o mais rápido possível.

Além das ligações diretas, o Mulher Amparada também oferece a funcionalidade de envio de localização em tempo real. Essa função permite que a usuária compartilhe sua posição atual com contatos de confiança ou serviços de apoio, facilitando o resgate e aumentando a precisão no atendimento. Em situações de perigo, muitas vezes a pessoa não consegue explicar onde está, e essa funcionalidade resolve esse problema de forma automática e eficiente.

Outro ponto importante do aplicativo é a simplicidade da interface. O design foi desenvolvido para ser intuitivo, direto e fácil de usar, mesmo sob pressão. Em momentos de estresse ou medo, a usuária não pode perder tempo tentando entender como o aplicativo funciona. Por isso, cada elemento foi pensado para ser acessado rapidamente, com botões grandes, claros e objetivos, garantindo que qualquer pessoa consiga utilizar sem dificuldade.

O botão de emergência (**SOS**) é um dos principais destaques do sistema. Ele foi criado para oferecer uma ação rápida em situações críticas, permitindo que a usuária inicie um pedido de ajuda com apenas um toque. Esse tipo de recurso é essencial em momentos onde não há tempo para navegar por menus ou realizar várias ações, garantindo rapidez e eficiência no acionamento de suporte.

O Mulher Amparada também se preocupa com a privacidade e segurança da usuária. O aplicativo pode incluir áreas protegidas, como conteúdos acessíveis apenas por biometria, garantindo que informações sensíveis fiquem protegidas contra acessos não autorizados. Essa camada adicional de segurança ajuda a preservar dados importantes e aumenta a confiança no uso da ferramenta.

Além de ser útil em situações extremas, o aplicativo também funciona como uma ferramenta preventiva. Ele pode ser utilizado no dia a dia, trazendo mais tranquilidade ao sair de casa, viajar, estudar ou trabalhar. Saber que existe um recurso pronto para ser usado em caso de necessidade gera uma sensação maior de controle e segurança.

O projeto Mulher Amparada nasce com a proposta de utilizar a tecnologia como aliada na proteção feminina. Em vez de depender apenas de ações externas, o aplicativo coloca um recurso direto nas mãos da usuária, permitindo que ela tenha mais autonomia para agir em situações de risco. Essa abordagem fortalece a prevenção e contribui para um ambiente mais seguro.

Com o avanço da tecnologia, soluções digitais como o Mulher Amparada se tornam cada vez mais relevantes. A possibilidade de integrar comunicação, localização e acesso a serviços essenciais em um único lugar torna o aplicativo uma ferramenta poderosa no apoio à segurança. O objetivo é continuar evoluindo, adicionando novas funcionalidades e melhorando a experiência para atender cada vez melhor as necessidades das usuárias.

O Mulher Amparada não é apenas um aplicativo, mas uma iniciativa que busca fazer a diferença na vida das mulheres, oferecendo suporte, segurança e praticidade. Ao unir tecnologia, acessibilidade e propósito, o projeto se posiciona como uma solução importante para quem busca mais proteção no dia a dia.

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

a GravarActivity.java usa a permissao de microfone + icones que sao xml em res/drawable, para a gravacao + animação de fade in e out