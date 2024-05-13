package com.example.dikiditest.data.modelDto

import com.example.dikiditest.domain.model.ImageModel

data class ImageModelDto(
    var thumb  : String? = null,
    var origin : String? = null

){
    companion object{
        fun imageDtoToImage(imageModelDto: ImageModelDto): ImageModel {
            return ImageModel(
                thumb = imageModelDto.thumb,
                origin = imageModelDto.origin
            )
        }
    }
}