package com.example.todoappjetpackcompose

import android.content.Context
import androidx.compose.runtime.snapshots.SnapshotStateList
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

const val FILE_NAME = "todo_list.dat"

fun writeToFile(items: SnapshotStateList<String>, context: Context) {
    val fos = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE)
    val oos = ObjectOutputStream(fos)
    val itemList = ArrayList<String>()
    itemList.addAll(items)
    oos.writeObject(itemList)
    oos.close()
}

fun readData(context: Context): SnapshotStateList<String> {
    var itemList: ArrayList<String>

    val fis = context.openFileInput(FILE_NAME)
    val ois = ObjectInputStream(fis)
    itemList = ois.readObject() as ArrayList<String>

    val items = SnapshotStateList<String>()
    items.addAll(itemList)
    ois.close()

    return items
}