# Mulher Amparada

O **Mulher Amparada** é um aplicativo de segurança e proteção desenvolvido especialmente para mulheres que desejam se sentir mais seguras em situações do dia a dia. O objetivo principal do aplicativo é oferecer suporte rápido, eficiente e acessível em momentos de risco, permitindo que a usuária consiga pedir ajuda de forma simples e imediata. Em um cenário onde muitas mulheres enfrentam situações de vulnerabilidade, ter uma ferramenta confiável pode fazer toda a diferença, e é exatamente isso que o Mulher Amparada busca proporcionar.

O aplicativo foi pensado para funcionar como um apoio digital em momentos críticos, permitindo acesso rápido a serviços essenciais como **Polícia (190)**, **SAMU (192)** e **Central de Atendimento à Mulher (180)**. Com apenas alguns toques, a usuária pode realizar ligações de emergência, reduzindo o tempo de reação em situações onde cada segundo é importante. Essa agilidade pode ser decisiva para evitar agravamentos e garantir assistência o mais rápido possível.

**Funções:**

Disfarce do app (calculadora falsa!):
tutorial: quando ele for iniciado, ele pedirá para criar uma senha (e salva em uma classe kt de criptografia), assim so acessa com a senha informada, para resetar essa senha (dê 5 toques em menos de 2 segundos, e digite como você gosta de ser chamada, e digite sua nova senha!), mas antes dessa tela, tem outra tipo uma gaveta de apps...

Botão de Pânico:
Botão de Pânico, com ligação ao 180 de forma direta no primeiro clique.

Proteção por Barulho:
Ative a proteção, faça barulho alto e ele liga para o 180.

Balançar o Celular para Pedir Ajuda:
Ative e, ao chacoalhar o celular, ele liga para o 180.

Área Protegida:
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

Calendário Menstrual:
Registre como dói cada dia e, com isso, o aplicativo monta um calendário.

Calendário de eventos:
Registra eventos da usuária quando ela precisar

Rotina:
Sistema de pontos, com registro de comidas e bebidas boas e ruins, bem como a adição de registro de exercícios físicos fáceis, médios ou difíceis e contagem de tempo de cada um deles, além de sistema de nível e conquistas.

Mapa:
Mostra um mapa da região da usuária

Diário:
Usando criptografia, a usuária poderá anotar o que quiser. Com a senha, ficará seguro e também não some, pois estará guardado.

Relógio:
Mostra o mapa do local atual, o país e outros dados, bem como o ano, semestre, bimestre, mês, quinzena, semana, dia, hora, minuto e segundo.

Além disso...,
Além de mostrar hora, minuto e segundo de todos os países.

Calculadora:
A calculadora pode ser usada para cálculos rápidos do dia a dia.

Tarefas:
O sistema permite categorizar tarefas em áreas como estudos, trabalho, pessoal e saúde.

As tarefas podem ser marcadas como concluídas para acompanhamento do progresso.

Todas as tarefas são salvas diretamente no navegador do usuário.

Os dados ficam armazenados localmente no dispositivo do usuário.

Gravador de voz:
Usando uma activity (uma tela) em kotlin, é possivel ter um gravador de voz no app, sendo possível registrar evidências e provas, além do que a usuária quiser, sempre usando permissoes android e com o consentimento da usuária!

Meus arquivos:
Dentro do app, ele só visualiza as pastas e ao clicar em um arquivo, abre um seletor de apps para executar/visualizar ele

Escolher ícone do app:
Essa função abre uma acitvity (tela) e poderá trocar o ícone do app!, até mesmo para um ícone de calculadora, disfarçando o app!, e outra, quando escolhe calculadora, até o nome do app troca tambem! (e da para desfazer os efeitos para o original se quiser...)

Desligar o celular:
Ao tocar neste botão, o aplicativo solicitará a permissão de Administrador do dispositivo, caso ela ainda não tenha sido concedida. Quando essa permissão estiver ativa, o aplicativo poderá bloquear imediatamente a tela do dispositivo.

Tela de aplicativos:
Ao tocar neste botão, o app mostrará um site dentro do app que lista todos os outros apps com a permissão query all packpages, combina muito se a usuária definir o app do mulher amparada como launcher padrão, assim, o agressor só acessa os outros apps com o mulher amparada, através da biometria desbloqueando a área segura!


Para desinstalar o aplicativo, primeiro será necessário desativar a permissão de Administrador do dispositivo. Em alguns aparelhos, também poderá ser necessário permitir Configurações restritas. Se essa opção estiver disponível, pressione e segure o ícone do aplicativo, toque em Informações do aplicativo, abra o menu de três pontos e ative Permitir configurações restritas.

Antes de conceder essa permissão, leia atentamente as informações exibidas pelo Android e só prossiga se compreender as funcionalidades e os efeitos dessa autorização.

Controlar brilho:
com isso, voce pode controlar o brilho da tela clicando em um botão..., porém, e tipo como se fosse o menor brilho do celular, e ai depois ele deixa a tela preta (nao com brilho e sim colocando a cor), (honestamente, antes aparecia as duas barras, agora elas se escondem!), e o efeito e vitalicio ate fechar e abrir o app!

Emergência:
Saindo dessa área, existem botões que abrem o aplicativo nativo do telefone nos números 190, 191, 192 e 180.

Contatos de Confiança:
Além dos contatos de confiança, clicando no primeiro botão você seleciona e salva o contato. O botão abaixo envia um pedido de ajuda para ele.

Widgets:
Já saindo do app, temos os widgets.

Widget SOS:
O primeiro é um widget de SOS que, quando clicado, abre o aplicativo de telefone com o número 180 já discado.

Widget de Localização:
O segundo, o de localização, monta o link do Google Maps, e a usuária pode mandar para quem quiser.

Widget de Proteção Sonora:
E o terceiro é apenas um acesso rápido para o serviço de proteção por palmas.

Compartilhamento Rápido de Localização:
Além disso, existe um botão dentro do app que pega a localização atual, monta um link do Google Maps e já manda para o WhatsApp do 180, precisando apenas clicar no botão de enviar.

(como o código esta dentro do zip, nao precisarei explicar!)

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
        • Alô e da companhia de energia
        • Alô é da energia
        • Alô e da energia
        • é da energia
        • e da companhia de energia
        • É da companhia de energia


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
        
**E eu não fiz curso nem nada, só usei a I.A (o ChatGPT) e o que aprendi de programação com ela!**

# Sobre a permissão de sms:

é um avanço, pois é uma permissão a menos pro app!, deixei esses registros dessa permissão nesse repositório porque pode ser que precise... (esse aviso serve apenas pro app do Mulher Amparada!)
       
# Sobre como os apps adicionais são abertos:

Esses apps são abertos por intent (todos que não são o mulher amparada), e não está disponível na tela de aplicativos, aumentandk a segurança, já que só abre pelo app mulher amparada pela área protegida e para abrir o app pede a biometria dele!

---

## 💗 Mensagem de Acolhimento às Usuárias

Se você busca segurança ou apoio, leia nossa **[Carta de Apoio e Propósito do Desenvolvedor](ABOUT.md)**.

Conheça nossa rede de suporte e lembre-se: **você não está sozinha.**

---

## ℹ️ Sobre o Desenvolvedor

Se você busca entender mais sobre mim, leia a **[Trajetória do Desenvolvedor](DEV.MD)**.

Saiba que eu sempre procuro entender vocês e que este projeto foi criado com o propósito de oferecer apoio, acolhimento e segurança.

----

# Direitos que toda mulher tem!

Conheça 100 direitos e garantias assegurados às mulheres pela legislação brasileira.

Para consultar a legislação completa e as referências utilizadas nesta seção, acesse **[LEIS.md](LEIS.md)**.

Conhecer seus direitos é importante para reconhecer situações de proteção, buscar ajuda quando necessário e entender as garantias previstas em lei.

----

# Conhecimentos para recuperar sua autonomia

Conhecimentos e informações para ajudar você a compreender melhor sua vida financeira, organizar seu dinheiro e fortalecer sua autonomia.

Para acessar o conteúdo completo sobre finanças, consulte **[Finanças](FINANÇAS.md)**.

---

## 💌 Carta de Agradecimentos

Se você deseja conhecer as pessoas, profissionais e artistas que fizeram parte desta trajetória, leia a **[Carta de Agradecimentos](AGRADECIMENTOS.md)**.

Nela, deixo registrado todo o meu carinho e gratidão àqueles que, de alguma forma, contribuíram para que este projeto existisse.

**Obrigado a todos que fizeram parte dessa história. ❤️**

---
# 💻 Repositórios do GitHub

> 🚀 **Código-fonte dos nossos aplicativos**
>
> Aqui você encontra os repositórios oficiais com o código-fonte dos nossos apps.

---

## 🛡️ Mulher Amparada

Aplicativo de proteção e segurança.

🔗 **[Acessar repositório](https://github.com/mulher-amparada/mulher-amparada-code)**

---

## 📁 Gerenciador de Arquivos do Mulher Amparada

Gerenciador de arquivos desenvolvido para o ecossistema do Mulher Amparada.

🔗 **[Acessar repositório](https://github.com/mulher-amparada/gerenciador-de-arquivos-do-mulher-amparada-code)**

---

## 🎙️ Assistente de Voz do Mulher Amparada

Assistente de voz desenvolvido para o Mulher Amparada.

🔗 **[Acessar repositório](https://github.com/mulher-amparada/assistente-de-voz-do-mulher-amparada-code)**

---

### 📚 Código aberto para estudo e desenvolvimento

Todos os projetos acima possuem seus respectivos repositórios no GitHub, permitindo consultar e acompanhar o código-fonte.

> ❤️ **Mulher Amparada**  
> Tecnologia desenvolvida para proteção, acessibilidade e segurança.

---

# Considerações finais:

eu penso que o android studio ou o github actions quando da erro, uma ia navega em todo o projeto e resolve (auto fix)

e os audios do gravador de voz também são criptografados com a classe Cripto

e o recurso de proteção ppr barulho, chacalhoar o celular e escurecer a tela, tem como ativar e desativar!

todas as activitys tem a flag secure de todos os 3 apps

e no application do AndroidManifest dos 3 apps tem allowBackup="false" 

O aplicativo possui um navegador interno. A navegação para o Google é feita diretamente pelo código usando window location eplace(), sem disponibilizar o endereço como um link na interface. O aplicativo também não implementa um sistema próprio de registro de histórico de navegação.

----

Lembre-se que o desenvolvedor só tem 15 anos e as pessoas ao redor dele nao tem os recursos para postar o app na play store, e ele é de menor o que piora mais ainda!

e ele também acredita que o vidro fosco também deveria ser aplicado nos popups (tipo o do instalador do pacote ou o biometricPrompt), idéia para a samsung aí hein!