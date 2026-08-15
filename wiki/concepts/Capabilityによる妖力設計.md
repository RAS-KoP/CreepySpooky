---
type: concept
title: "Capabilityによる妖力設計"
created: 2026-08-04
updated: 2026-08-04
tags:
  - yoryoku
  - capability
  - neoforge
  - energy
status: developing
complexity: advanced
domain: "[[wiki/domains/minecraft-mods/_index]]"
aliases:
  - "妖力システム"
  - "Yoriki energy"
related:
  - "[[wiki/entities/CreepySpooky]]"
  - "[[wiki/concepts/NeoForge MOD開発]]"
sources:
  - "[[wiki/sources/妖力実装記録]]"
---

# Capabilityによる妖力設計

## 目的

Botaniaのマナのように、ブロック間で物を動かす動力源を追加する。独自APIを増やさず、NeoForgeのEnergy Capabilityを接続面として利用する。

## 現在の仕様

| 機能 | 容量 | 入力 | 出力 |
| --- | ---: | ---: | ---: |
| クリエイティブ妖力プール | 無限 | 0/t | 64/t |
| 妖力送信機 | 256 | 64/t | 無線64/t |
| 妖力受信機 | 256 | 無線64/t | 正面64/t |
| 妖力かまど | 256 | 16/t | なし |
| 妖力接続ケーブル | 256 | 64/t | 64/t |

## 責務分担

- `YorikiEnergyStorage`: 容量と入出力上限を持つストレージ
- `YorikiTransfer`: 隣接ブロックへの転送処理
- `YorikiCapabilityRegister`: BlockへのEnergy Capability公開
- 各BlockEntity: 自身の状態、tick処理、NBT保存
- `YorikiLinkingWandItem`: 送信機と受信機の接続情報を登録・解除

## データフロー

```text
クリエイティブ妖力プール
  → 妖力送信機
  == 無線接続 ==>
  妖力受信機
  → 妖力かまど / 他の機械
```

接続先の座標とディメンションはBlockEntityのNBTへ保存し、ワールド再起動後も復元する。同じディメンションの送信機と受信機だけを接続対象とする。

## 重要な検証点

- 送信・受信の1 tick上限が仕様値を超えないこと。
- かまどが処理中だけ16妖力/tを消費すること。
- クライアント表示とサーバー側の実際のCapability状態がずれないこと。
- 破棄済みブロック、異なるディメンション、無効なNBTに対して安全に動作すること。
