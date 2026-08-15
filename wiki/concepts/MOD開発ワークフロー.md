---
type: concept
title: "MOD開発ワークフロー"
created: 2026-08-04
updated: 2026-08-04
tags:
  - workflow
  - git
  - testing
  - minecraft-modding
status: developing
complexity: basic
domain: "[[wiki/domains/minecraft-mods/_index]]"
aliases:
  - "MOD開発プロセス"
related:
  - "[[wiki/entities/CreepySpooky]]"
  - "[[wiki/sources/RAS-KoPガイドライン]]"
sources:
  - "[[wiki/sources/RAS-KoPガイドライン]]"
---

# MOD開発ワークフロー

## 標準フロー

1. Issueに概要・目的・完了条件を書く。
2. `develop` から変更種別に応じたブランチを作る。
3. 設計資料で対象範囲、データ、互換性、テスト条件を決める。
4. 小さな単位で実装し、コメントと変更理由を残す。
5. `compileJava`、`test`、`build` を実行する。
6. PRに概要、追加・変更・修正点、AI利用範囲を書く。
7. 完了条件を確認してレビュー・マージする。
8. 実装記録とWikiの設計判断を更新する。

## Gitの命名

`feat/`, `fix/`, `asset/`, `docs/`, `release/` など、変更の種類を英語で表す。コミット・Issue・PRには `[feat]` などの接頭辞を付ける。

## AI利用

AIは一メソッド単位のコード補助と文書化に限定するというガイドラインがある。PRでは利用範囲を明記し、生成物を検証してから採用する。

## 検証の記録

成功したコマンド、失敗したコマンド、既知の未検証事項を実装記録とWiki質問ノートに分けて残す。
