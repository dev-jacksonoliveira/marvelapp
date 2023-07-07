## Marvel App

O Marvel App é um projeto simples que possui uma interface visualmente atraente e exibe uma lista de
quadrinhos, juntamente com alguns detalhes obtidos da API da Marvel.

## Sobre a API Key

É necessário ter a chave de api pública e privada para realizar o build.
Para criar as chaves acesse
-> [Marvel developer portal](https://developer.marvel.com/documentation/getting_started)

## Para construir o projeto

Insira as chaves no arquivo gradle.properties e sincronize o projeto.

```
marvel_public_api_key=<YOUR_PUBLIC_API_KEY>
marvel_private_api_key=<YOUR_PUBLIC_API_KEY>
```

### Sobre o projeto

O projeto foi desenvolvido seguindo a arquitetura MVVM, com a divisão em camadas de data, model e
presentation. Ele utiliza injeção de dependências e navegação entre fragmentos. 
![home_marvel_app](https://github.com/dev-jacksonoliveira/challenge-marvelapp/assets/59921189/7d68a4e7-9cec-458e-92fe-7c4cc94d5cba)
![Uploading detail_screen.png…]()
