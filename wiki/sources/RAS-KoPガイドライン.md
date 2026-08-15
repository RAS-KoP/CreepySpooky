---
type: source
title: "RAS-KoPガイドライン"
created: 2026-08-04
updated: 2026-08-04
tags:
  - source
  - process
  - git
  - ai-policy
status: mature
source_type: project-document
author: "RAS-KoP"
date_published: 2026-08-04
url: ""
confidence: high
key_claims:
  - "CreepySpookyはNeoForge 21.1.247環境を対象に開発する。"
  - "Issue・コミット・PRには変更種別の接頭辞を付ける。"
  - "AI利用範囲はPRに明記し、コード利用は一メソッド単位に制限する。"
related:
  - "[[wiki/concepts/MOD開発ワークフロー]]"
  - "[[wiki/entities/CreepySpooky]]"
sources:
  - "[[docs/GUIDELINES]]"
---

# RAS-KoPガイドライン

一次資料は `docs/GUIDELINES.md`。このページは、MOD開発Wikiで繰り返し参照するルールの要約である。

## 開発ルール

- コメントを残し、チームで読める実装にする。
- Issueは概要・目的・完了条件を含め、一つの変更に絞る。
- コミットは `[feat]`、`[fix]`、`[docs]` などの接頭辞と簡潔な説明を使う。
- `develop` を開発の基準ブランチとし、変更種別に応じた英語のブランチ名を使う。
- PRには概要、追加点、変更点、修正点、AI利用範囲を記載する。

## AI利用の扱い

AIによるコード補助は一メソッド単位に限定し、ブラックボックス化を避ける。設計・実装・テストの結果は人間が確認し、PRで利用範囲を開示する。
