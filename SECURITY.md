# 🔐 Política de Segurança

## Mulher Amparada

A segurança do **Mulher Amparada** é uma prioridade.

Por se tratar de um aplicativo que possui recursos relacionados à proteção, privacidade e segurança pessoal, qualquer vulnerabilidade identificada no projeto deve ser tratada com responsabilidade e comunicada aos mantenedores de forma adequada.

Esta política explica como reportar vulnerabilidades de segurança, quais informações podem ser fornecidas e como esses relatos serão tratados.

---

## 📋 Índice

- [Versões suportadas](#-versões-suportadas)
- [O que é uma vulnerabilidade](#-o-que-é-uma-vulnerabilidade)
- [Como reportar uma vulnerabilidade](#-como-reportar-uma-vulnerabilidade)
- [Informações que devem ser incluídas](#-informações-que-devem-ser-incluídas)
- [Divulgação responsável](#-divulgação-responsável)
- [Processo de análise](#-processo-de-análise)
- [Classificação de severidade](#-classificação-de-severidade)
- [Testes de segurança](#-testes-de-segurança)
- [Proteção de dados](#-proteção-de-dados)
- [Escopo](#-escopo)
- [Reconhecimento](#-reconhecimento)
- [Problemas comuns](#-problemas-comuns)
- [Contato](#-contato)

---

## 🛡️ Versões suportadas

Recomendamos utilizar sempre a versão mais recente do **Mulher Amparada** disponibilizada pelo projeto.

| Versão | Status |
|---|---|
| Versão mais recente | ✅ Suportada |
| Versões anteriores | ⚠️ Suporte limitado |
| Versões descontinuadas | ❌ Não suportadas |

Uma vulnerabilidade encontrada em uma versão antiga poderá ser analisada, especialmente quando também estiver presente na versão atualmente suportada.

---

## ⚠️ O que é uma vulnerabilidade?

Consideramos uma vulnerabilidade qualquer falha que possa comprometer a segurança, privacidade, integridade ou funcionamento esperado do aplicativo.

Entre os exemplos estão:

- Bypass de mecanismos de proteção;
- Falhas de autenticação ou autorização;
- Acesso não autorizado a informações;
- Exposição de dados que deveriam permanecer protegidos;
- Armazenamento inadequado de informações sensíveis;
- Falhas na implementação de mecanismos criptográficos;
- Vazamento de credenciais, tokens ou chaves;
- Acesso indevido a arquivos;
- Execução não autorizada de funcionalidades;
- Falhas relacionadas ao Android, Kotlin, JavaScript ou WebView;
- Falhas que possam comprometer recursos de segurança do aplicativo.

Esta lista não é exaustiva.

Um comportamento inesperado não significa necessariamente que exista uma vulnerabilidade. Cada relatório será analisado individualmente.

---

## 🚨 Como reportar uma vulnerabilidade

### ⚠️ Não utilize Issues públicas

**Não publique vulnerabilidades de segurança em Issues, Pull Requests ou discussões públicas.**

A divulgação pública antes da existência de uma correção pode permitir que outras pessoas explorem o problema e aumentar o risco para os usuários.

Utilize um canal privado de contato com os responsáveis pelo projeto.

Caso o GitHub disponibilize um mecanismo privado de reporte de vulnerabilidades para o repositório, ele deverá ser utilizado preferencialmente.

---

## 📝 Informações que devem ser incluídas

Um bom relatório deve conter o máximo possível de informações relevantes.

Você pode utilizar o seguinte modelo:

Título:
[Resumo curto da vulnerabilidade]

Versão afetada:
[Versão do Mulher Amparada]

Componente afetado:
[Componente ou funcionalidade]

Descrição:
[Explique claramente o problema]

Impacto:
[Explique quais consequências podem ocorrer]

Passos para reprodução:
[Descreva como reproduzir o problema]

Ambiente:
[Modelo do dispositivo e versão do Android, quando relevante]

Evidências:
[Capturas de tela, registros ou outras evidências]

Sugestão de correção:
[Opcional]

Não é necessário possuir todas essas informações para realizar um reporte.

Um relatório incompleto ainda poderá ser analisado.

---

## 🔍 Processo de análise

Após o recebimento de um relatório, os responsáveis pelo projeto poderão:

1. Confirmar o recebimento;
2. Analisar as informações fornecidas;
3. Tentar reproduzir o comportamento;
4. Identificar os componentes afetados;
5. Determinar as versões afetadas;
6. Avaliar o impacto e a severidade;
7. Desenvolver ou avaliar uma correção;
8. Testar a correção;
9. Publicar uma atualização, quando necessário;
10. Documentar a resolução de forma responsável.

O tempo necessário para cada etapa depende da complexidade e do impacto da vulnerabilidade.

---

## 📊 Classificação de severidade

As vulnerabilidades poderão ser classificadas de acordo com seu impacto:

| Severidade | Descrição |
|---|---|
| 🔴 Crítica | Vulnerabilidade com potencial de causar comprometimento grave da segurança, exposição significativa de dados ou controle não autorizado de recursos importantes. |
| 🟠 Alta | Vulnerabilidade com impacto significativo que pode permitir ações não autorizadas ou comprometer recursos importantes. |
| 🟡 Média | Vulnerabilidade com impacto relevante, normalmente dependente de determinadas condições para ser explorada. |
| 🟢 Baixa | Vulnerabilidade com impacto limitado ou com condições de exploração mais restritivas. |
| ⚪ Informativa | Problema ou comportamento que não representa um risco significativo à segurança. |

A classificação definitiva será determinada após a análise técnica.

---

## 🧪 Testes de segurança

Pesquisas e testes de segurança devem ser realizados de forma responsável e somente em ambientes para os quais o pesquisador possui autorização.

Não realize testes que possam:

- Acessar dados de terceiros;
- Utilizar contas de terceiros;
- Comprometer dispositivos de outras pessoas;
- Alterar ou excluir dados sem autorização;
- Interromper deliberadamente serviços;
- Causar danos ao aplicativo ou aos usuários;
- Expor informações privadas;
- Afetar sistemas externos sem autorização.

Sempre que possível, utilize dispositivos, contas e dados especificamente destinados a testes.

---

## 🔒 Proteção de dados

Relatórios de segurança podem conter informações potencialmente sensíveis.

**Nunca envie dados pessoais ou confidenciais desnecessários.**

Utilize dados fictícios ou anonimizados sempre que possível.

Não inclua no relatório:

- Senhas;
- Tokens de autenticação;
- Chaves privadas;
- Credenciais;
- Dados pessoais;
- Arquivos pessoais;
- Informações de terceiros;
- Informações que não sejam necessárias para reproduzir o problema.

Caso uma evidência contenha informações sensíveis, remova essas informações antes de enviá-la.

---

## 🎯 Escopo

Esta política se aplica ao código e às funcionalidades desenvolvidas e disponibilizadas pelo projeto **Mulher Amparada** neste repositório:

https://github.com/mulher-amparada/mulher-amparada-app

O escopo pode incluir, entre outros:

- Código Android;
- Kotlin;
- JavaScript;
- HTML;
- CSS;
- WebView;
- Armazenamento local;
- Autenticação;
- Sistemas de proteção;
- Criptografia;
- Gerenciamento de arquivos;
- Comunicação entre componentes;
- Configurações de segurança;
- Recursos desenvolvidos especificamente para o aplicativo.

Bibliotecas, sistemas operacionais, APIs, serviços externos e plataformas de terceiros podem possuir suas próprias políticas de segurança e devem ser reportados aos respectivos responsáveis quando apropriado.

---

## 🏆 Reconhecimento

Agradecemos pesquisadores, desenvolvedores e colaboradores que contribuam de forma responsável para a segurança do projeto.

Quando apropriado, o projeto poderá reconhecer publicamente pesquisadores que tenham reportado vulnerabilidades de maneira responsável.

Qualquer reconhecimento público poderá ser realizado somente quando isso não comprometer a privacidade ou a segurança do pesquisador.

---

## 📢 Problemas comuns

Issues públicas podem ser utilizadas para relatar:

- Bugs que não representem riscos de segurança;
- Problemas de funcionamento;
- Sugestões;
- Melhorias;
- Problemas de interface;
- Outras questões que não contenham informações sensíveis.

Se houver dúvida sobre a natureza de um problema, prefira tratá-lo inicialmente como um possível problema de segurança e utilize um canal privado.

---

## 📬 Contato

Para reportar uma vulnerabilidade de segurança, utilize um canal privado disponibilizado pelos responsáveis pelo projeto.

### Repositório oficial

https://github.com/mulher-amparada/mulher-amparada-app

Caso o repositório disponibilize o recurso **Security Advisories / Report a vulnerability**, utilize esse mecanismo preferencialmente para reportes relacionados à segurança.

---

## ❤️ Obrigado

Agradecemos a todos que dedicam tempo para identificar e comunicar problemas de segurança de forma responsável.

Cada reporte válido pode ajudar a:

- Melhorar a segurança do aplicativo;
- Corrigir vulnerabilidades;
- Proteger os usuários;
- Aumentar a qualidade do código;
- Tornar o projeto mais confiável.

> Segurança, privacidade e responsabilidade são compromissos contínuos do projeto Mulher Amparada.

Escopo


Reconhecimento


Contato





🛡️ Versões suportadas


Sempre que possível, mantenha o aplicativo atualizado para a versão mais recente disponibilizada pelo projeto.




Versão
Status




Última versão estável
✅ Suportada


Versões anteriores
⚠️ Suporte limitado


Versões descontinuadas
❌ Não suportadas




Uma vulnerabilidade encontrada exclusivamente em uma versão antiga poderá ser analisada conforme a possibilidade de reproduzi-la e seu impacto.



⚠️ O que é uma vulnerabilidade?


Consideramos vulnerabilidade qualquer falha que possa comprometer a segurança, privacidade, integridade ou disponibilidade do aplicativo ou de seus dados.


Exemplos incluem:




Bypass de mecanismos de proteção;


Falhas de autenticação ou autorização;


Exposição indevida de informações;


Armazenamento inseguro de dados;


Falhas relacionadas à criptografia;


Vazamento de credenciais, chaves ou tokens;


Execução não autorizada de funcionalidades;


Acesso indevido a arquivos ou dados;


Falhas que permitam modificar informações sem autorização;


Problemas de segurança no código Android, JavaScript, Kotlin ou WebView;


Vulnerabilidades que possam colocar usuários em risco.




Esta lista não é exaustiva.



🚨 Como reportar uma vulnerabilidade


❗ Não abra uma Issue pública


Se o problema possuir impacto de segurança, não publique os detalhes em uma Issue, Pull Request ou discussão pública.


Um relatório público pode permitir que outras pessoas explorem a vulnerabilidade antes que exista uma correção.


Sempre que possível, utilize um canal privado de comunicação com os responsáveis pelo projeto.



📝 Informações recomendadas


Para facilitar a investigação, procure incluir:


Título:
[Resumo curto da vulnerabilidade]

Versão afetada:
[Versão do aplicativo]

Componente afetado:
[Ex.: WebView, armazenamento, autenticação, etc.]

Descrição:
[Explique o problema]

Impacto:
[Explique o que pode acontecer]

Reprodução:
[Passos necessários para reproduzir]

Ambiente:
[Versão do Android e outras informações relevantes]

Evidências:
[Capturas de tela ou outros dados necessários]

Sugestão de correção:
[Opcional]



Não é necessário fornecer todas essas informações caso não estejam disponíveis.


Um relatório incompleto ainda poderá ser analisado.



🔎 Processo de análise


Após receber um relatório, a equipe responsável poderá:




Analisar a descrição da vulnerabilidade;


Tentar reproduzir o comportamento informado;


Determinar quais versões são afetadas;


Avaliar o impacto e a gravidade;


Desenvolver ou avaliar uma correção;


Testar a correção;


Publicar uma nova versão, quando necessário;


Divulgar informações sobre a vulnerabilidade de forma responsável.




O tempo necessário para cada etapa pode variar de acordo com a complexidade do problema.



📊 Classificação de gravidade


As vulnerabilidades poderão ser avaliadas de acordo com seu impacto.




Nível
Descrição




🔴 Crítico
Pode causar comprometimento grave da segurança ou exposição significativa de dados.


🟠 Alto
Pode permitir ações não autorizadas ou comprometer funcionalidades importantes.


🟡 Médio
Possui impacto relevante, mas exige determinadas condições para exploração.


🟢 Baixo
Possui impacto limitado ou dificuldade elevada de exploração.


⚪ Informativo
Não representa uma vulnerabilidade de segurança significativa.




A classificação final será determinada pela equipe responsável após a análise técnica.



🔒 O que não fazer


Ao investigar ou reportar uma vulnerabilidade:




❌ Não tente acessar dados de outras pessoas;


❌ Não obtenha, copie ou divulgue informações privadas;


❌ Não utilize contas de terceiros;


❌ Não altere ou exclua dados de usuários;


❌ Não provoque indisponibilidade intencional do serviço;


❌ Não realize testes que possam causar danos a dispositivos;


❌ Não publique exploits ou instruções de exploração antes da correção;


❌ Não divulgue senhas, tokens, chaves privadas ou outras credenciais.




Utilize somente ambientes e dados que você tenha autorização para testar.



🕵️ Divulgação responsável


Solicitamos que vulnerabilidades sejam mantidas em sigilo durante o período necessário para investigação e correção.


Depois que uma vulnerabilidade for corrigida, o projeto poderá divulgar informações sobre:




Natureza do problema;


Versões afetadas;


Versão que contém a correção;


Impacto;


Medidas adotadas para corrigir o problema.




Informações que possam facilitar ataques contra usuários poderão ser omitidas ou reduzidas.



🔐 Dados sensíveis


O Mulher Amparada pode possuir funcionalidades que trabalham com informações que o usuário considera privadas.


Por isso, nunca envie dados reais de usuários em um relatório de segurança.


Ao demonstrar uma vulnerabilidade:




Utilize dados fictícios;


Remova informações pessoais;


Remova senhas;


Remova tokens;


Remova chaves criptográficas;


Remova identificadores desnecessários;


Não envie arquivos pessoais de terceiros.




Se uma evidência contiver informações sensíveis, remova-as antes do envio.



🎯 Escopo


Esta política está relacionada principalmente ao código e às funcionalidades disponibilizadas neste repositório do Mulher Amparada.


Podem fazer parte do escopo, quando aplicável:




Código Android;


Código Kotlin;


Código JavaScript;


HTML e CSS;


WebView;


Armazenamento local;


Sistemas de autenticação;


Sistemas de proteção;


Mecanismos de criptografia;


Gerenciamento de arquivos;


Comunicação entre componentes do aplicativo;


Configurações de segurança do aplicativo.




Serviços, bibliotecas, sistemas operacionais ou plataformas de terceiros podem possuir suas próprias políticas de segurança.



🧪 Testes de segurança


Testes de segurança devem ser realizados de maneira responsável e somente em dispositivos, contas e dados para os quais você possui autorização.


O projeto não autoriza testes que envolvam:




Dados de terceiros;


Contas de terceiros;


Sistemas externos sem autorização;


Interrupção deliberada de serviços;


Destruição ou alteração de dados;


Qualquer atividade que possa causar danos a outras pessoas.





🏆 Reconhecimento


Contribuições responsáveis para a segurança do projeto são valorizadas.


Quando apropriado e com autorização do pesquisador, o projeto poderá reconhecer publicamente a pessoa que reportou uma vulnerabilidade.


O reconhecimento não é automático e dependerá da análise do caso.



📢 Relatos públicos


Issues públicas são adequadas para bugs comuns, sugestões e problemas que não envolvam segurança.


Para vulnerabilidades de segurança, utilize comunicação privada.


Se você não tiver certeza se determinado problema deve ser tratado como uma vulnerabilidade, prefira não publicar os detalhes técnicos publicamente até obter orientação.



📬 Contato


Para informações sobre segurança ou para reportar uma vulnerabilidade, entre em contato com os responsáveis pelo projeto por um canal privado disponível no perfil ou na página oficial do projeto.


Repositório oficial:


https://github.com/mulher-amparada/mulher-amparada-app



❤️ Obrigado


Obrigado por ajudar a tornar o Mulher Amparada um projeto mais seguro.


A colaboração responsável da comunidade é fundamental para identificar problemas, melhorar o código e proteger os usuários.




Segurança é responsabilidade de todos.





Problemas exclusivamente relacionados a serviços de terceiros devem ser comunicados aos respectivos responsáveis, quando apropriado.


Compromisso com a segurança


Agradecemos a todos que contribuírem para tornar o Mulher Amparada mais seguro.


Relatos responsáveis de vulnerabilidades ajudam o projeto a identificar problemas, melhorar suas proteções e oferecer uma experiência mais segura aos usuários.

