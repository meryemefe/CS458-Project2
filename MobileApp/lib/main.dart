import 'dart:async';
import 'dart:html';

import 'package:flutter/material.dart';
import 'package:date_field/date_field.dart';

void main() {
  runApp(SurveyApp());
}

class SurveyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold (
        appBar: AppBar(
          title: new Center(
            child: Text("Covid-19 Survey", textAlign: TextAlign.center)
          ),
          backgroundColor: Colors.lightGreen,
        ),
        body: SurveyForm(),
      ),
    );
  }
}

class SurveyForm extends StatefulWidget {
  @override
  _SurveyFormState createState() => _SurveyFormState();
}

class _SurveyFormState extends State<SurveyForm> {
  final _surveyFormKey = GlobalKey<FormState>();
  String _chosenValue = 'Select a gender';
  @override
  Widget build(BuildContext context) {
    final halfScreenWidth = MediaQuery.of(context).size.width / 2.0;

    return Form(
      key: _surveyFormKey,
      child: Column(
        children: <Widget>[
          Row (
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              Container(
                alignment: Alignment.topLeft,
                width: halfScreenWidth,
                child: CustomTextFormField(
                  hintText: "Name",
                  validator: (String value) {
                    if (value.isEmpty) {
                      return 'Name cannot be empty.';
                    } else {
                      return null;
                    }
                  },
                ),
              ),
              Container(
                alignment: Alignment.topRight,
                width: halfScreenWidth,
                child: CustomTextFormField(
                  hintText: "Surname",
                  validator: (String value) {
                    if (value.isEmpty) {
                      return 'Surname cannot be empty.';
                    } else {
                      return null;
                    }
                  },
                ),
              ),
            ],
          ),
          Padding(
            padding: EdgeInsets.all(8.0),
            child: Container(
              child: DateTimeFormField(
                decoration: const InputDecoration(
                  hintStyle: TextStyle(color: Colors.black45),
                  errorStyle: TextStyle(color: Colors.redAccent),
                  border: OutlineInputBorder(
                      borderRadius: BorderRadius.all(Radius.circular(12.0))
                  ),
                  suffixIcon: Icon(Icons.event_note),
                  labelText: "Birth Date",
                  contentPadding: EdgeInsets.all(12),
                  filled: true,
                  fillColor: Colors.white,
                ),
                mode: DateTimeFieldPickerMode.date,
                autovalidateMode: AutovalidateMode.always,
                validator: (e) => (e?.day ?? 0) == 1 ? 'Please not the first day' : null,
                onDateSelected: (DateTime value) {
                  print(value);
                },
              ),
            ),
          ),

          CustomTextFormField( // BURAYA CITY LİSTE ŞEKLİNDE KONULABİLİR VEYA TEXT
            hintText: "City",
            validator: null,
          ),
          Row(
              crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>  [Container(
              alignment: Alignment.center,
              padding: EdgeInsets.all(4.0),
              margin: const EdgeInsets.only(left: 10.0),
              width: MediaQuery.of(context).size.width - 20,
              decoration: BoxDecoration(
                borderRadius: BorderRadius.circular(12.0),
                border: Border.all(color: Colors.black),
                color: Colors.white,
              ),
              child: DropdownButtonFormField<String>(
                isExpanded: true,
                focusColor:Colors.white,
                value: _chosenValue,
                style: TextStyle(color: Colors.white),
                iconEnabledColor:Colors.black,
                items: <String>[
                  'Select a gender',
                  'Male',
                  'Female'
                ].map<DropdownMenuItem<String>>((String value) {
                  return DropdownMenuItem<String>(
                    value: value,
                    child: Text(value,style:TextStyle(color:Colors.black),),
                  );
                }).toList(),
                hint:Text(
                  "Please choose a langauage",
                  style: TextStyle(
                      color: Colors.black,
                      fontSize: 14,
                      fontWeight: FontWeight.w500),
                ),
                validator: (String value){
                  if (value == 'Select a gender'){
                    return 'Please select a gender';
                  }else{
                    return null;
                  }
                },
                onChanged: (String value) {
                  setState(() {
                    _chosenValue = value;
                  });
                },
              ),
            ),]
          ),
          CustomTextFormField(
            hintText: "Gender", // BURAYA DROPDOWN KOYULACAK MALE FEMALE OTHER
            validator: null,
          ),
          CustomTextFormField( // BELLİ AŞI TİPLERİ LİSTE ŞEKLİNDE KOYULABİLİR
            hintText: "Vaccine type they applied",
          ),
          CustomTextFormField( // TEXT BOX BÜYÜTÜLEBİLİR
            hintText: "Side effect after vaccination",
          ),
          MaterialButton( // BUTONA BASINCA SAYFA DEĞİŞİP BAŞARI ŞEKİLDE GÖNDERİLDİ YAZDIRILABİLİR
            shape: RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(17.0),
            ),
            color: Colors.blueAccent,
            textColor: Colors.white,
            child: Text(
              "Send",
              style: TextStyle(
                color: Colors.white,
              ),
            ),
            onPressed: () async {
              if (_surveyFormKey.currentState.validate()) {}
            },
          ),
        ],
      )
    );
  }
}

class CustomTextFormField extends StatelessWidget {
  final String hintText;
  final Function validator;
  CustomTextFormField({
    this.hintText,
    this.validator
});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: EdgeInsets.all(8.0),
      child: TextFormField(
        validator: this.validator,
        decoration: InputDecoration(
          hintText: hintText,
          contentPadding: EdgeInsets.all(12),
          border: OutlineInputBorder(
            borderRadius: BorderRadius.circular(15.0),
          ),
          filled: true,
          fillColor: Colors.white,
        ),
      ),
    );
  }
}