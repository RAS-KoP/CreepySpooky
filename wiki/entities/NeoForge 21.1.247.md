---
type: entity
title: "NeoForge 21.1.247"
created: 2026-08-04
updated: 2026-08-04
tags:
  - neoforge
  - minecraft
  - mod-loader
status: developing
entity_type: product
role: "CreepySpookyが対象とするMODローダー環境"
first_mentioned: "[[wiki/sources/RAS-KoPガイドライン]]"
related:
  - "[[wiki/entities/CreepySpooky]]"
  - "[[wiki/concepts/NeoForge MOD開発]]"
sources:
  - "[[wiki/sources/RAS-KoPガイドライン]]"
---

# NeoForge 21.1.247

## 位置づけ

CreepySpookyの開発ガイドラインが示すMODローダー環境。プロジェクトの設定ファイルではMinecraft 1.21.1とNeoForge ModDev Gradle 2.0系を利用している。

## 確認ポイント

- NeoForge APIのバージョン差分を確認してから実装する。
- Capabilityやレジストリの実装は、現在のプロジェクトコードと公式ドキュメントを優先する。
- クライアント専用処理とサーバー共通処理を分離する。
- `./gradlew compileJava`, `./gradlew test`, `./gradlew build` を変更後の基本検証とする。
