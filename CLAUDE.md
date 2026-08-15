# CreepySpooky — MOD開発Wiki

このプロジェクトは、Claude CodeとObsidianを組み合わせたMOD開発用Vaultです。
Claude Codeでは `claude-obsidian` プラグインを使用し、ObsidianではこのリポジトリのルートをVaultとして開きます。

## プロジェクト

- 対象: Minecraft Java Edition 1.21.1
- MODローダー: NeoForge 21.1.247系
- 言語: Java 21
- ビルド: Gradle Wrapper (`./gradlew`)
- MOD ID: `creepyspooky`
- 設計・運用ガイド: [[docs/GUIDELINES]]

## Wiki Knowledge Base

Wikiの入口は [[wiki/index]] です。最近の文脈は [[wiki/hot]]、全体像は [[wiki/overview]] を先に確認します。

### 読み込み順

1. `wiki/hot.md` — 直近の変更と未解決事項
2. `wiki/index.md` — Wiki全体の索引
3. `wiki/domains/minecraft-mods/_index.md` — MOD開発ドメインの索引
4. 関連する個別ページと、必要に応じてプロジェクト内の実装ファイル

### 運用ルール

- ノートはYAML frontmatterとObsidian wikilinkを使う。
- `docs/` とコードは一次資料。Wikiには要約・設計判断・関係を記録する。
- `.raw/` に置いた取り込み元は不変資料として扱い、直接編集しない。
- `wiki/index.md`、関連サブインデックス、`wiki/log.md`、`wiki/hot.md` を更新する。
- 同じ知識のノートを重複作成せず、既存ページを更新する。
- 仕様が未確定な場合は断定せず、`wiki/questions/` または対象ノートの「未決事項」に記録する。
- 実装作業では `docs/GUIDELINES.md` のブランチ名、コミット、PR、AI利用ルールに従う。
- 一般的なJava質問や、Wikiに関係しないコード作業ではWikiを広範囲に読まない。

## claude-obsidian の使い方

- `/wiki` — Wikiの初期状態確認・構造整備
- `ingest <ファイル>` — 資料をWikiへ取り込む
- `query: <質問>` — Wikiを根拠に回答する
- `lint the wiki` — 孤立ノート・切れたリンク・不足情報を点検する
- `/save` — 会話から設計判断や作業記録を保存する

このプロジェクトにはVault用の `.mcp.json` も配置しています。Claude Codeを再起動すると、`obsidian-vault` がこのプロジェクトを対象にします。
