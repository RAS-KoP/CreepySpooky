---
type: entity
title: "GeckoLib"
created: 2026-08-04
updated: 2026-08-04
tags:
  - geckolib
  - animation
  - minecraft
status: seed
entity_type: product
role: "エンティティモデル・アニメーションを扱う依存ライブラリ"
first_mentioned: "[[wiki/entities/CreepySpooky]]"
related:
  - "[[wiki/concepts/NeoForge MOD開発]]"
  - "[[wiki/entities/CreepySpooky]]"
sources:
  - "[[wiki/entities/CreepySpooky]]"
---

# GeckoLib

## このプロジェクトでの利用

`build.gradle` でNeoForge向けGeckoLibを依存に追加し、テストエンティティのGeoモデル・アニメーション・レンダラーで利用している。

## 追加調査が必要な点

- 対応するGeckoLibの正確なバージョンとMinecraft版の対応表
- サーバー側でロードされないクライアント専用モデル処理の境界
- アニメーションJSONとJavaモデルの命名規則
