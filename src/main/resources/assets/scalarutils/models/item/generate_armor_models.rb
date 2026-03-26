require 'json'

# ── Configuration ─────────────────────────────────────────────────────────────

ARMOR_TYPE = "umbralite"
MOD_ID     = "scalarutils"

PIECES = %w[helmet chestplate leggings boots]

# Full trim list sourced directly from minecraft-patched-26.1.0.1-beta-merged.jar
TRIMS = %w[
  amethyst
  copper
  diamond
  emerald
  gold
  iron
  lapis
  netherite
  quartz
  redstone
  resin
]

OUTPUT_DIR = File.dirname(__FILE__)

# ── Helpers ───────────────────────────────────────────────────────────────────

def base_model(piece)
  {
    "parent"   => "minecraft:item/generated",
    "textures" => {
      "layer0" => "#{MOD_ID}:item/#{ARMOR_TYPE}_#{piece}"
    }
  }
end

def trim_model(piece, trim)
  {
    "parent"   => "minecraft:item/generated",
    "textures" => {
      "layer0" => "#{MOD_ID}:item/#{ARMOR_TYPE}_#{piece}",
      "layer1" => "minecraft:trims/items/#{piece}_trim_#{trim}"
    }
  }
end

def write(filename, data)
  path = File.join(OUTPUT_DIR, filename)
  File.write(path, JSON.pretty_generate(data) + "\n")
  puts "  wrote #{filename}"
end

# ── Generation ────────────────────────────────────────────────────────────────

puts "Generating armor models for '#{ARMOR_TYPE}' (#{PIECES.size} pieces × #{TRIMS.size + 1} variants)..."
puts

total = 0

PIECES.each do |piece|
  puts "#{piece}:"

  write("#{ARMOR_TYPE}_#{piece}.json", base_model(piece))
  total += 1

  TRIMS.each do |trim|
    write("#{ARMOR_TYPE}_#{piece}_#{trim}_trim.json", trim_model(piece, trim))
    total += 1
  end

  puts
end

puts "Done — #{total} files written to #{OUTPUT_DIR}"

