---
type: source
title: "CreepySpookyコードベース"
created: 2026-08-04
updated: 2026-08-04
tags:
  - source
  - codebase
  - architecture
  - minecraft-modding
status: developing
source_type: repository
author: "CreepySpooky team"
date_published: 2026-08-04
url: ""
confidence: medium
key_claims:
  - "Javaの登録・共通処理・クライアント処理・ゲーム要素の実装をパッケージで分けている。"
  - "assetsとdataをMOD IDの名前空間で管理し、Java側の登録IDと対応付ける。"
  - "妖力関連のBlockEntityと登録クラスが、現在の主要な拡張ポイントである。"
related:
  - "[[wiki/entities/CreepySpooky]]"
  - "[[wiki/concepts/NeoForge MOD開発]]"
  - "[[wiki/concepts/Capabilityによる妖力設計]]"
sources:
  - "[[wiki/entities/CreepySpooky]]"
---

# CreepySpookyコードベース

このページは、プロジェクトのファイル構成をWikiから引くための要約である。正確な挙動・型・仕様値は常に現在のコードを確認する。

## Java構成

- `CreepySpooky.java` — MODエントリポイントと共通登録
- `CreepySpookyClient.java` — クライアント専用登録
- `register/` — Block、BlockEntity、Entity、Capability等の登録
- `yoriki/` — 妖力の定数、ストレージ、転送、Block、BlockEntity、接続アイテム
- `entity/`, `model/`, `renderer/` — テストエンティティとGeckoLib関連
- `Config.java` — NeoForge設定

## リソース構成

- `assets/creepyspooky/blockstates/` — ブロック状態
- `assets/creepyspooky/models/` — ブロック・アイテムモデル
- `assets/creepyspooky/textures/` — テクスチャ
- `assets/creepyspooky/lang/` — 翻訳
- `data/creepyspooky/loot_tables/` — ブロックドロップ

## 変更時の確認

Java登録、BlockEntity、JSONモデル、翻訳、ドロップ、テクスチャを機能単位で確認し、`docs/YORIKI_IMPLEMENTATION.md` と [[wiki/concepts/Capabilityによる妖力設計]] の仕様値を同期する。
