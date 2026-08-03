# CreepySpooky

NeoForge 21.1.247向けのCreepySpooky MODです。

## 開発環境

- Minecraft 1.21.1
- NeoForge 21.1.247
- Java 21

## ソース構成

```text
src/main/java/io/github/ras_kop/creepyspooky/
├── block/             ブロック本体
│   └── entity/        ブロックエンティティ
├── client/            クライアント専用処理
├── entity/            通常のエンティティ
├── item/              アイテム本体
├── model/             エンティティモデル
├── register/          NeoForge登録処理
├── renderer/          エンティティ描画
└── yoriki/            妖力の共通ストレージ・転送処理
```

リソースはMinecraftの標準構成に従い、`src/main/resources/assets/creepyspooky` と
`src/main/resources/data/creepyspooky` に配置します。

妖力の仕様、使い方、変更ログは [docs/YORIKI_IMPLEMENTATION.md](docs/YORIKI_IMPLEMENTATION.md) を参照してください。

## ビルドと検証

```bash
./gradlew test build
```

ゲームを起動する場合は、次のタスクを使用します。

```bash
./gradlew runClient
./gradlew runServer
```

ブランチ、コミット、PRの命名規則は [docs/GUIDELINES.md](docs/GUIDELINES.md) に従います。
