---
type: concept
title: "NeoForge MOD開発"
created: 2026-08-04
updated: 2026-08-04
tags:
  - neoforge
  - minecraft-modding
  - architecture
status: developing
complexity: intermediate
domain: "[[wiki/domains/minecraft-mods/_index]]"
aliases:
  - "NeoForge modding"
related:
  - "[[wiki/entities/NeoForge 21.1.247]]"
  - "[[wiki/entities/CreepySpooky]]"
  - "[[wiki/concepts/Capabilityによる妖力設計]]"
sources:
  - "[[wiki/sources/RAS-KoPガイドライン]]"
---

# NeoForge MOD開発

## 基本構成

CreepySpookyでは、MOD本体クラス、レジストリ、BlockEntity、クライアント専用登録、リソース定義を分離している。

```text
MODエントリポイント
  ├─ ブロック・アイテム登録
  ├─ BlockEntity登録
  ├─ Capability登録
  ├─ 共通イベント / サーバー処理
  └─ クライアント登録・レンダリング

resources/
  ├─ assets/<modid>/  # モデル・テクスチャ・翻訳
  └─ data/<modid>/    # ルートテーブル等
```

## 実装の観点

- 登録対象はDeferredRegisterに集約する。
- BlockEntityは保存データ、tick処理、Capability公開を責務として分ける。
- クライアント専用クラスはサーバーで参照されないようにする。
- JSONリソースのIDはJava側の登録IDと一致させる。
- APIの正確な型・メソッドは、プロジェクトの依存バージョンに合わせて確認する。

## 関連する一次資料

- [[wiki/entities/NeoForge 21.1.247]]
- [[wiki/entities/CreepySpooky]]
- `build.gradle`
- `src/main/java/io/github/ras_kop/creepyspooky/`
