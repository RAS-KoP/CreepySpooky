---
type: overview
title: "CreepySpooky Knowledge Base Overview"
created: 2026-08-04
updated: 2026-08-04
tags:
  - creepyspooky
  - overview
  - minecraft-modding
status: developing
related:
  - "[[wiki/index]]"
  - "[[wiki/domains/minecraft-mods/_index]]"
sources:
  - "[[wiki/sources/RAS-KoPガイドライン]]"
  - "[[wiki/sources/妖力実装記録]]"
---

# CreepySpooky Knowledge Base Overview

## 目的

Minecraft Java Edition 1.21.1 / NeoForge 21.1.247系で開発しているCreepySpookyの知識を、実装コードだけでなく、設計意図・API選定・テスト・運用判断まで検索可能な形で蓄積する。

## 現在の開発テーマ

中心テーマは、Botaniaのマナのような動力源としての「妖力」である。現在の実装記録では、[[wiki/concepts/Capabilityによる妖力設計]] に示す以下の構成が対象になっている。

- クリエイティブ妖力プール
- 妖力送信機・妖力受信機
- 妖力かまど
- 妖力接続ケーブル
- 妖力接続の杖

## 知識の層

1. **一次資料** — `docs/`, `local/`, `src/`, `build.gradle`, `gradle.properties`
2. **Wiki要約** — `wiki/sources/`, `wiki/concepts/`, `wiki/entities/`
3. **判断・運用** — `wiki/meta/`, `wiki/questions/`, `wiki/log.md`

## 技術的な基準点

- NeoForgeのEnergy Capabilityを接続APIとして利用する。
- 仕様値はコード内の定数と設計資料の両方で確認する。
- クライアント表示、サーバー側の状態、BlockEntityの保存データを分けて考える。
- 実装前に設計資料、実装後にビルド・テスト・ログを更新する。
- AI利用範囲は [[wiki/sources/RAS-KoPガイドライン]] のチームルールに従う。

## 使い方

Claude Codeでは `query: <質問>` で設計・実装知識を問い合わせ、資料を追加するときは `.raw/` に置いて `ingest <ファイル>` を実行する。Obsidianではリンクグラフとバックリンクで、機能・クラス・設計判断のつながりを確認する。
