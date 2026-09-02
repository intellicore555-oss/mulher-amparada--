# Mulher Amparada

O **Mulher Amparada** é um aplicativo de segurança e proteção desenvolvido especialmente para mulheres que desejam se sentir mais seguras em situações do dia a dia. O objetivo principal do aplicativo é oferecer suporte rápido, eficiente e acessível em momentos de risco, permitindo que a usuária consiga pedir ajuda de forma simples e imediata. Em um cenário onde muitas mulheres enfrentam situações de vulnerabilidade, ter uma ferramenta confiável pode fazer toda a diferença, e é exatamente isso que o Mulher Amparada busca proporcionar.

O aplicativo foi pensado para funcionar como um apoio digital em momentos críticos, permitindo acesso rápido a serviços essenciais como **Polícia (190)**, **SAMU (192)** e **Central de Atendimento à Mulher (180)**. Com apenas alguns toques, a usuária pode realizar ligações de emergência, reduzindo o tempo de reação em situações onde cada segundo é importante. Essa agilidade pode ser decisiva para evitar agravamentos e garantir assistência o mais rápido possível.

**Funções:**

Disfarce do app (calculadora falsa!):
tutorial: quando ele for iniciado, ele pedirá para criar uma senha (e salva em uma classe kt de criptografia), assim so acessa com a senha informada, para resetar essa senha (dê 5 toques em menos de 2 segundos, e digite como você gosta de ser chamada, e digite sua nova senha!), mas antes dessa tela, tem outra tipo uma gaveta de apps..., porém, agora no mulher amparada, ele já vem com o icone de calculadora e o nome calculadora, só dá para mudar o icone, (o gerenciador de arquivos virou Explorador de arquivos com seu icone e o assistente de voz virou Saúde & Fitness com seu icone, os dois podem ser visto com os dados originais dentro da área protegida), isto significa que o disfarce já está dentro do packpage id inicial!

Botão de Pânico:
Botão de Pânico, com ligação ao 180 de forma direta no primeiro clique.

Proteção por Barulho:
Ative a proteção, faça barulho alto e ele liga para o 180.

Balançar o Celular para Pedir Ajuda:
Ative e, ao chacoalhar o celular, ele liga para o 180.

Escurecimento por inclinação:
com isso, voce pode controlar o brilho da tela clicando em um botão..., porém, e tipo como se fosse o menor brilho do celular, e ai depois ele deixa a tela preta (nao com brilho e sim colocando a cor), (honestamente, antes aparecia as duas barras, agora elas se escondem!), e o efeito e vitalicio ate fechar e abrir o app!

Emergência:
Saindo dessa área, existem botões que abrem o aplicativo nativo do telefone nos números 190, 191, 192 e 180.

Compartilhamento Rápido de Localização:
Além disso, existe um botão dentro do app que pega a localização atual, monta um link do Google Maps e já manda para o WhatsApp do 180, precisando apenas clicar no botão de enviar.

Contatos de Confiança:
Além dos contatos de confiança, clicando no primeiro botão você seleciona e salva o contato. O botão abaixo envia um pedido de ajuda para ele.

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

GRAVAÇÕES — COMPORTAMENTO E METADADOS

1. DURANTE A GRAVAÇÃO

Ao tocar no botão de gravação:

• O aplicativo inicia a gravação de áudio pelo microfone.
• É criado temporariamente um arquivo .3gp, (ou um arquivo 3ga, ou outro arquivo, isso varia conforme o aparelho).
• É registrado o horário exato de início da gravação.
• O nível da bateria é registrado.
• O acelerômetro começa a acompanhar os movimentos do aparelho e registra o maior valor de força G observado durante a gravação.
• Se as permissões de localização estiverem disponíveis, o aplicativo acompanha a localização e registra latitude, longitude, precisão e provedor.
• O arquivo original permanece temporário durante o processo.

2. AO ENCERRAR A GRAVAÇÃO

Quando a gravação é encerrada:

• É registrado o horário exato de encerramento.
• É calculado o SHA-256 do áudio original.
• O áudio é criptografado usando AES/GCM.
• O arquivo protegido recebe a extensão .enc.
• É calculado o SHA-256 do arquivo criptografado.
• É criado um arquivo separado .metadata.json contendo os metadados.
• Depois que a criptografia é concluída, o .3gp, (ou um arquivo 3ga, ou outro arquivo, isso varia conforme o aparelho) original temporário é apagado do armazenamento privado do aplicativo.
• A gravação protegida e seus metadados aparecem na lista do aplicativo.

3. METADADOS GERADOS

TEMPO:

• created_at — timestamp UNIX do início.
• created_at_utc — início em UTC.
• closed_at — timestamp UNIX do encerramento.
• closed_at_utc — encerramento em UTC.
• last_modified — última modificação registrada pelo sistema.
• last_modified_utc — última modificação em UTC.
• ntp_synced — indicação relacionada à configuração de hora automática do Android.

INTEGRIDADE E CRIPTOGRAFIA:

• sha256_raw — SHA-256 do áudio original antes da criptografia.
• sha256_encrypted — SHA-256 do arquivo .enc.
• crypto_algorithm — AES/GCM/NoPadding, 256-bit.
• key_provider — AndroidKeyStore.

LOCALIZAÇÃO:

• gps_latitude — latitude registrada.
• gps_longitude — longitude registrada.
• gps_accuracy — precisão estimada em metros.
• location_provider — provedor utilizado, como GPS ou rede.

DISPOSITIVO:

• device_model — modelo do aparelho.
• device_brand — fabricante/marca.
• android_version — versão do Android.
• api_level — nível da API.
• device_hash — identificador derivado do Android ID e protegido por SHA-256.

SENSORES E AMBIENTE:

• max_g_force — maior aceleração registrada pelo acelerômetro durante a gravação.
• battery_level — nível da bateria no início da gravação.

ARQUIVO:

• file_name — nome do arquivo criptografado.
• file_size_bytes — tamanho do arquivo .enc.
• file_format — formato original do áudio, 3gp, (ou um arquivo 3ga, ou outro arquivo, isso varia conforme o aparelho)/AMR-NB.
• metadata_version — versão do formato dos metadados.

4. O QUE ACONTECE AO TOCAR EM "DOWNLOAD"

O botão de download NÃO simplesmente copia o .enc.

O aplicativo:

1. Localiza o arquivo .enc protegido.
2. Descriptografa temporariamente o conteúdo.
3. Cria um arquivo .3gp, (ou um arquivo 3ga, ou outro arquivo, isso varia conforme o aparelho) temporário no cache do aplicativo.
4. Copia esse áudio para a pasta Downloads do Android.
5. No Android 10 ou superior, utiliza o MediaStore.
6. Em versões antigas, utiliza a pasta pública Downloads.
7. Depois da cópia, o arquivo .3gp, (ou um arquivo 3ga, ou outro arquivo, isso varia conforme o aparelho) temporário utilizado durante o processo é apagado.
8. O arquivo .enc original continua protegido dentro do aplicativo.

5. IMPORTANTE SOBRE O JSON

O .metadata.json é um arquivo separado do áudio.

Por exemplo:

Downloads/

    rec_1787821440.3gp, (ou um arquivo 3ga, ou outro arquivo, isso varia conforme o aparelho)
    rec_1787821440.metadata.json

O .3gp, (ou um arquivo 3ga, ou outro arquivo, isso varia conforme o aparelho) é o áudio reproduzível.

O .metadata.json contém as informações técnicas associadas àquela gravação.

6. IMPORTANTE SOBRE A LOCALIZAÇÃO

A localização não é garantida em todas as gravações.

Se a permissão de localização estiver concedida e o Android fornecer uma localização válida, os campos de localização são preenchidos.

Caso contrário, eles ficam como null.

7. IMPORTANTE SOBRE OS METADADOS

Esses metadados são registros técnicos produzidos pelo aplicativo. Eles podem ajudar a documentar como e quando uma gravação foi criada, mas a existência de hashes, localização ou timestamps, por si só, NÃO garante validade jurídica ou prova que um fato ocorreu.

O SHA-256 permite verificar se os bytes de um arquivo correspondem ao conteúdo anteriormente registrado, enquanto os dados de localização, sensores e horário dependem dos recursos e configurações do próprio aparelho.

Meus arquivos:
Dentro do app, ele só visualiza as pastas e ao clicar em um arquivo, abre um seletor de apps para executar/visualizar ele

Desligar o celular:
Ao tocar neste botão, o aplicativo solicitará a permissão de Administrador do dispositivo, caso ela ainda não tenha sido concedida. Quando essa permissão estiver ativa, o aplicativo poderá bloquear imediatamente a tela do dispositivo.

Tela de aplicativos:
Ao tocar neste botão, o app mostrará um site dentro do app que lista todos os outros apps com a permissão query all packpages...


Para desinstalar o aplicativo, primeiro será necessário desativar a permissão de Administrador do dispositivo. Em alguns aparelhos, também poderá ser necessário permitir Configurações restritas. Se essa opção estiver disponível, pressione e segure o ícone do aplicativo, toque em Informações do aplicativo, abra o menu de três pontos e ative Permitir configurações restritas.

Antes de conceder essa permissão, leia atentamente as informações exibidas pelo Android e só prossiga se compreender as funcionalidades e os efeitos dessa autorização.

(como o código esta dentro do zip, nao precisarei explicar!)

❤️Mulher Amparada❤️, um projeto totalmente gratuito e livre de anúncios, projetado por um menino autista nível 1 de 15 anos!, usando o apoio do chatgpt, sem curso formal!