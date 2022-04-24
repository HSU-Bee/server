window.swaggerSpec={
  "openapi" : "3.0.1",
  "info" : {
    "title" : "PETRA API",
    "description" : "PETRA API description",
    "version" : "0.1.0"
  },
  "servers" : [ {
    "url" : "http://localhost:8080"
  } ],
  "tags" : [ ],
  "paths" : {
    "/login" : {
      "post" : {
        "tags" : [ "login" ],
        "summary" : "로그인 후 쿠키 발급",
        "description" : "로그인 후 쿠키를 발급받을 수 있습니다.",
        "operationId" : "LogIn and Get Cookie",
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/login486549215"
              },
              "examples" : {
                "LogIn and Get Cookie" : {
                  "value" : "{\"id\":\"heather\",\"name\":\"������\"}"
                }
              }
            }
          }
        },
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/login-1664081379"
                },
                "examples" : {
                  "LogIn and Get Cookie" : {
                    "value" : "{\r\n  \"code\" : 2000,\r\n  \"message\" : \"성공\"\r\n}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/users/travel-types" : {
      "post" : {
        "tags" : [ "users" ],
        "summary" : "여행 타입 부여",
        "description" : "사용자의 응답을 기반으로 여행 타입을 부여합니다.",
        "operationId" : "Grant TravelType",
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/users-travel-types2059588752"
              },
              "examples" : {
                "Grant TravelType" : {
                  "value" : "{\r\n  \"id\" : \"park1\",\r\n  \"answer\" : [ \"b\", \"b\", \"b\", \"b\", \"b\", \"b\", \"b\", \"b\", \"b\", \"b\" ]\r\n}"
                }
              }
            }
          }
        },
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/users-travel-types-980480359"
                },
                "examples" : {
                  "Grant TravelType" : {
                    "value" : "{\r\n  \"typeName\" : \"양귀비<위로, 위안>\",\r\n  \"typeDesc\" : \"도비 이즈 프리!! 도비는 자유를 찾아 떠나요~ 양귀비의 꽃말 위로, 위안과 잘 어울리는 당신! 여행을 떠날때는 주로 위로와 위안을 찾아 떠납니다. 당신은 때로는 힘든 생기면 도피하고 싶은 경향이 있습니다. 힘든 일상으로부터 도망치듯이 여행을 떠나는 당신은 여행에서 휴식과 편안함을 가장 중요하게 여기네요. 당신에게 추천하는 여행은 주로 호캉스, 스파, 식물원 등이 있습니다~\",\r\n  \"typeImageUrl\" : \"https://petra-bucket.s3.ap-northeast-2.amazonaws.com/psychology-test/%EB%A9%A7%EB%B0%AD%EC%A5%90.jpg\"\r\n}"
                  }
                }
              }
            }
          }
        }
      }
    }
  },
  "components" : {
    "schemas" : {
      "users-travel-types2059588752" : {
        "type" : "object",
        "properties" : {
          "answer" : {
            "type" : "array",
            "description" : "밸런스 게임 응답",
            "items" : {
              "oneOf" : [ {
                "type" : "object"
              }, {
                "type" : "boolean"
              }, {
                "type" : "string"
              }, {
                "type" : "number"
              } ]
            }
          },
          "id" : {
            "type" : "string",
            "description" : "사용자 ID"
          }
        }
      },
      "users-travel-types-980480359" : {
        "type" : "object",
        "properties" : {
          "typeDesc" : {
            "type" : "string",
            "description" : "여행 타입 설명"
          },
          "typeImageUrl" : {
            "type" : "string",
            "description" : "여행 타입 이미지"
          },
          "typeName" : {
            "type" : "string",
            "description" : "여행 타입 이름"
          }
        }
      },
      "login-1664081379" : {
        "type" : "object",
        "properties" : {
          "code" : {
            "type" : "number",
            "description" : "응답코드"
          },
          "message" : {
            "type" : "string",
            "description" : "응답메시지"
          }
        }
      },
      "login486549215" : {
        "type" : "object"
      }
    }
  }
}