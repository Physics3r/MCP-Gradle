package net.minecraft.world.gen.layer;

import net.minecraft.world.biome.BiomeGenBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GenLayerHills extends GenLayer {
    private static final Logger logger = LogManager.getLogger();
    private final GenLayer field_151628_d;

    public GenLayerHills(long p_i45479_1_, GenLayer p_i45479_3_, GenLayer p_i45479_4_) {
        super(p_i45479_1_);
        this.parent = p_i45479_3_;
        this.field_151628_d = p_i45479_4_;
    }

    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
        int[] aint = this.parent.getInts(areaX - 1, areaY - 1, areaWidth + 2, areaHeight + 2);
        int[] aint1 = this.field_151628_d.getInts(areaX - 1, areaY - 1, areaWidth + 2, areaHeight + 2);
        int[] aint2 = IntCache.getIntCache(areaWidth * areaHeight);

        for (int i = 0; i < areaHeight; ++i) {
            for (int j = 0; j < areaWidth; ++j) {
                this.initChunkSeed(j + areaX, i + areaY);
                int k = aint[j + 1 + (i + 1) * (areaWidth + 2)];
                int l = aint1[j + 1 + (i + 1) * (areaWidth + 2)];
                boolean flag = (l - 2) % 29 == 0;

                if (k > 255) {
                    logger.debug("old! " + k);
                }

                if (k != 0 && l >= 2 && (l - 2) % 29 == 1 && k < 128) {
                    if (BiomeGenBase.getBiome(k + 128) != null) {
                        aint2[j + i * areaWidth] = k + 128;
                    } else {
                        aint2[j + i * areaWidth] = k;
                    }
                } else if (this.nextInt(3) != 0 && !flag) {
                    aint2[j + i * areaWidth] = k;
                } else {
                    int i1 = k;

                    if (k == BiomeGenBase.desert.biomeID) {
                        i1 = BiomeGenBase.desertHills.biomeID;
                    } else if (k == BiomeGenBase.forest.biomeID) {
                        i1 = BiomeGenBase.forestHills.biomeID;
                    } else if (k == BiomeGenBase.birchForest.biomeID) {
                        i1 = BiomeGenBase.birchForestHills.biomeID;
                    } else if (k == BiomeGenBase.roofedForest.biomeID) {
                        i1 = BiomeGenBase.plains.biomeID;
                    } else if (k == BiomeGenBase.taiga.biomeID) {
                        i1 = BiomeGenBase.taigaHills.biomeID;
                    } else if (k == BiomeGenBase.megaTaiga.biomeID) {
                        i1 = BiomeGenBase.megaTaigaHills.biomeID;
                    } else if (k == BiomeGenBase.coldTaiga.biomeID) {
                        i1 = BiomeGenBase.coldTaigaHills.biomeID;
                    } else if (k == BiomeGenBase.plains.biomeID) {
                        if (this.nextInt(3) == 0) {
                            i1 = BiomeGenBase.forestHills.biomeID;
                        } else {
                            i1 = BiomeGenBase.forest.biomeID;
                        }
                    } else if (k == BiomeGenBase.icePlains.biomeID) {
                        i1 = BiomeGenBase.iceMountains.biomeID;
                    } else if (k == BiomeGenBase.jungle.biomeID) {
                        i1 = BiomeGenBase.jungleHills.biomeID;
                    } else if (k == BiomeGenBase.ocean.biomeID) {
                        i1 = BiomeGenBase.deepOcean.biomeID;
                    } else if (k == BiomeGenBase.extremeHills.biomeID) {
                        i1 = BiomeGenBase.extremeHillsPlus.biomeID;
                    } else if (k == BiomeGenBase.savanna.biomeID) {
                        i1 = BiomeGenBase.savannaPlateau.biomeID;
                    } else if (biomesEqualOrMesaPlateau(k, BiomeGenBase.mesaPlateau_F.biomeID)) {
                        i1 = BiomeGenBase.mesa.biomeID;
                    } else if (k == BiomeGenBase.deepOcean.biomeID && this.nextInt(3) == 0) {
                        int j1 = this.nextInt(2);

                        if (j1 == 0) {
                            i1 = BiomeGenBase.plains.biomeID;
                        } else {
                            i1 = BiomeGenBase.forest.biomeID;
                        }
                    }

                    if (flag && i1 != k) {
                        if (BiomeGenBase.getBiome(i1 + 128) != null) {
                            i1 += 128;
                        } else {
                            i1 = k;
                        }
                    }

                    if (i1 == k) {
                        aint2[j + i * areaWidth] = k;
                    } else {
                        int k2 = aint[j + 1 + (i + 1 - 1) * (areaWidth + 2)];
                        int k1 = aint[j + 1 + 1 + (i + 1) * (areaWidth + 2)];
                        int l1 = aint[j + 1 - 1 + (i + 1) * (areaWidth + 2)];
                        int i2 = aint[j + 1 + (i + 1 + 1) * (areaWidth + 2)];
                        int j2 = 0;

                        if (biomesEqualOrMesaPlateau(k2, k)) {
                            ++j2;
                        }

                        if (biomesEqualOrMesaPlateau(k1, k)) {
                            ++j2;
                        }

                        if (biomesEqualOrMesaPlateau(l1, k)) {
                            ++j2;
                        }

                        if (biomesEqualOrMesaPlateau(i2, k)) {
                            ++j2;
                        }

                        if (j2 >= 3) {
                            aint2[j + i * areaWidth] = i1;
                        } else {
                            aint2[j + i * areaWidth] = k;
                        }
                    }
                }
            }
        }

        return aint2;
    }
}
