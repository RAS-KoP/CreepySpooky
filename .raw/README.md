# Raw sources

このディレクトリは、`claude-obsidian` がWikiへ取り込む一次資料の置き場です。

- 取り込み元は追加後に変更しない。
- 新しい資料は日付や出典が分かるファイル名で保存する。
- Claude Codeで `ingest .raw/<ファイル名>` を実行すると、`wiki/sources/` と関連ノートが更新される。
- プロジェクト内の既存資料（`docs/`, `local/`, `src/`）を正とし、Wikiは要約・リンク・判断を管理する。
