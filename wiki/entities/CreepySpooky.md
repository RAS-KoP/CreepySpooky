---
type: entity
title: "CreepySpooky"
created: 2026-08-04
updated: 2026-08-04
tags:
  - creepyspooky
  - repository
  - minecraft-mod
status: developing
entity_type: repository
role: "NeoForge向けMinecraft MODの開発プロジェクト"
first_mentioned: "[[wiki/sources/RAS-KoPガイドライン]]"
related:
  - "[[wiki/domains/minecraft-mods/_index]]"
  - "[[wiki/concepts/NeoForge MOD開発]]"
  - "[[wiki/concepts/Capabilityによる妖力設計]]"
sources:
  - "[[wiki/sources/RAS-KoPガイドライン]]"
  - "[[wiki/sources/妖力実装記録]]"
---

# CreepySpooky

## 概要

Minecraft Java Edition 1.21.1向けのNeoForge MOD。MOD IDは `creepyspooky`。現在は妖力を使ったブロック間の動力転送を中心に開発している。

## リポジトリの重要箇所

- `src/main/java/io/github/ras_kop/creepyspooky/` — Java実装
- `src/main/resources/assets/creepyspooky/` — モデル、テクスチャ、翻訳、アニメーション
- `src/main/resources/data/creepyspooky/` — ルートテーブル等のデータ
- `docs/` — 開発ガイドラインと実装記録
- `local/` — 設計書・企画書のLaTeX資料
- `build.gradle`, `gradle.properties` — NeoForge・依存・バージョン設定

## 開発上の注意

ブランチ、コミット、PRの接頭辞、AI利用範囲は `docs/GUIDELINES.md` を正とする。実装の仕様値は [[wiki/concepts/Capabilityによる妖力設計]] と実コードの両方を確認する。
