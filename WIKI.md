# CreepySpooky Wiki

このファイルは、`claude-obsidian` がこのプロジェクトをMOD開発用の永続知識DBとして扱うための入口です。

## 目的

NeoForge 1.21.1向けMinecraft MOD「CreepySpooky」の設計、実装、テスト、互換性、運用に関する知識を、コードと設計資料にリンクした形で蓄積します。

## 構造

```text
.raw/                         # 取り込み元の不変資料
wiki/index.md                 # 全体索引
wiki/hot.md                   # 最近の文脈キャッシュ
wiki/overview.md              # Wikiの全体像
wiki/domains/minecraft-mods/  # MOD開発ドメイン
wiki/concepts/                # API・設計パターン・開発手法
wiki/entities/                # プロジェクト・ライブラリ・リポジトリ
wiki/sources/                 # docs/等の資料の要約
wiki/questions/               # 未解決の技術質問・調査結果
wiki/meta/                    # ダッシュボード・運用情報
_templates/                   # MOD開発用ノートテンプレート
```

## ノートの原則

- 1ノート1テーマ。長くなったら分割する。
- すべてのWikiノートに `type`, `title`, `created`, `updated`, `tags`, `status` を含める。
- ノート間の関係は `[[ノート名]]` で表す。
- ソースの内容と、このプロジェクト固有の判断を混同しない。
- 重要な設計変更は、理由・代替案・影響範囲を残す。
- 未確認の情報は `confidence: low` または未決事項として扱う。

## 取り込みの入口

既存の設計資料はすでに `wiki/sources/` に要約しています。新しいNeoForgeドキュメント、Issue、PR、テスト結果、調査メモは `.raw/` に置いてから、Claude Codeで次のように処理します。

```text
ingest .raw/<source-file>
```

質問は次の形式で行います。

```text
query: 妖力送信機と受信機の責務分担を説明して
```
